package com.codigosandroid.utils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 17/11/2017.
 */

public class IOUtil<T> {

    private static final String TAG = IOUtil.class.getSimpleName();

    /** Converte a InputStream para String utilizando o charset informado
     * @param in Objeto InputStream
     * @param charset tipo do charset do arquivo
     * @return
     * @throws  IOException */
    public static String toString(InputStream in, String charset) throws IOException {
        byte[] bytes = toBytes(in);
        String texto = new String(bytes, charset);
        return texto;
    }

    /**
     * Converte a InputStream para bytes[]
     * @param in
     * @return */
    public static byte[] toBytes(InputStream in) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            byte[] bytes = bos.toByteArray();
            return bytes;
        } catch (Exception e) {
            LogUtil.error(TAG, e.getMessage(), e);
            return null;
        } finally {
            try {
                bos.close();
                in.close();
            } catch (IOException e) {
                LogUtil.error(TAG, e.getMessage(), e);
            }
        }
    }

    public static void writeString(OutputStream out, String string) {
        writeBytes(out, string.getBytes());
    }

    public static void writeBytes(OutputStream out, byte[] bytes) {
        try {
            out.write(bytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            LogUtil.error(TAG, e.getMessage(), e);
        }
    }

    /**
     * Salva o texto em arquivo
     * @param file
     * @param  string*/
    public static void writeString(File file, String string) {
        writeBytes(file, string.getBytes());
    }

    // Escrevendo os bytes no arquivo
    public static void writeBytes(File file, byte[] bytes) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            LogUtil.error(TAG, e.getMessage(), e);
        }
    }

    // Lê uma String do arquivo
    public static String readString(File file) {
        try {
            if (file == null || !file.exists()) {
                return null;
            }
            InputStream in = new FileInputStream(file);
            String s =  toString(in, "UTF-8");
            return s;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            LogUtil.error(TAG, e.getMessage(), e);
            return null;
        }
    }

    /** Salva a figura em arquivo
     * @param file
     * @param bitmap */
    public static void writeBitmap(File file, Bitmap bitmap) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (IOException e) {
            LogUtil.error(TAG, e.getMessage(), e);
        }
    }

    public interface Callback {
        void onFileSave(File file, boolean exists);
    }

    /** Salva o bitmap em arquivo. Utiliza a URL para descobrir o nome.
     * @param  url URL original par extrair o nome do arquivo
     * @param bitmap Bitmap que já está em memória
     * @param  callback Interface de retorno */
    public static void saveBitmapToFile(String url, Bitmap bitmap, Callback callback) {
        try {
            if (url == null || bitmap == null && callback != null) {
                return;
            }

            String fileName = url.substring(url.lastIndexOf("/"));

            File file = SDCardUtil.getPublicFile(fileName, Environment.DIRECTORY_PICTURES);

            if (file.exists()) {
                callback.onFileSave(file, true);
            } else {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.close();

                LogUtil.debug(TAG, "Save File: " + file);

                // Salva o arquivo
                IOUtil.writeBitmap(file, bitmap);
                callback.onFileSave(file, false);
            }
        } catch (IOException e) {
            LogUtil.error(TAG, e.getMessage(), e);
        }
    }

    public static boolean downloadToFile(String url, File file) {
        try {
            InputStream in = new URL(url).openStream();
            byte[] bytes = IOUtil.toBytes(in);
            IOUtil.writeBytes(file, bytes);
            LogUtil.debug(TAG, "downloadToFile: " + file);
            return true;
        } catch (IOException e) {
            LogUtil.error(TAG, e.getMessage(), e);
            return false;
        }
    }

    public static void setWriteFile(File file, String text) {
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {

            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);

            bw.write(text);
            bw.flush();
            bw.close();

        } catch (Exception e) {
            LogUtil.error(TAG, e.getMessage(), e);
        } finally {
            try { out.close(); } catch (IOException e) {
                LogUtil.error(TAG, e.getMessage(), e);
            }
        }
    }

    public static List<String> getRowsFile(File file) {
        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            in = new FileInputStream(file);
            isr = new InputStreamReader(in);
            br = new BufferedReader(isr);

            String linha;
            List<String> linhas = new ArrayList<>();

            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (Exception e) {
            LogUtil.error(TAG, e.getMessage(), e);
            return null;
        } finally {
            try { in.close(); } catch (IOException e) {
                LogUtil.error(TAG, e.getMessage(), e);
            }
        }
        return null;
    }

    // Lê um arquivo Json
    public static BufferedReader readJson(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    // Carraga uma lista de objetos apartir de um arquivo .json
    public List<T> getJsonToObject(Context context, String name) throws FileNotFoundException {
        File file = FileUtil.getFile(context, name);

        if (file.exists()) {
            Gson gson = new Gson();
            BufferedReader br = IOUtil.readJson(file);
            ListObjectUtil listObjects = gson.fromJson(br, ListObjectUtil.class);
            return (List<T>) listObjects.getObjectUtils();
        }
        return null;
    }
}

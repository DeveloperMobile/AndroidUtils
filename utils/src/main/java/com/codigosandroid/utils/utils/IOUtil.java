package com.codigosandroid.utils.utils;

import android.content.Context;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 17/11/2017.
 */

public class IOUtil<T> {

    private static final String TAG = IOUtil.class.getSimpleName();

    /** Converte a InputStream para String utilizando o charset informado
     * @param in Objeto InputStream
     * @param charset tipo do charset do arquivo */
    public static String toString(InputStream in, String charset) throws IOException {

        byte[] bytes = toBytes(in);
        String texto = new String(bytes, charset);
        return texto;

    }

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

    // Escrevendo uma string no arquivo
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

            InputStream in = new FileInputStream(file);
            return toString(in, "UTF-8");

        } catch (Exception e) {

            LogUtil.error(TAG, e.getMessage(), e);
            return null;

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

            try {

                out.close();

            } catch (IOException e) {

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

            try {
                in.close();
            } catch (IOException e) {
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

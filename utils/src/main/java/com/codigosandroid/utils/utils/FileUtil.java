package com.codigosandroid.utils.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tiago on 17/11/2017.
 */

public class FileUtil {

    /** Retorna o caminho do arquivo para ser salvo na memória interna
     * @param context contexto da classe usuária do método
     * @param  name nome do arquivo */
    public static File getFile(Context context, String name) {

        return context.getFileStreamPath(name);

    }

    /** Retorna o OutputStream para salvar o arquivo na memória interna.
     * @param context contexto da classe usuária do método
     * @param name nome do arquivo*/
    public static FileOutputStream getFileOutput(Context context, String name) throws FileNotFoundException {

        // Cria o arquivo e retorna o OutputStream
        return context.openFileOutput(name, Context.MODE_PRIVATE);

    }

    /** Retorna a InputStream para ler o arquivo da memória interna
     * @param context contexto da classe usuária do método
     * @param name nome do arquivo */
    public static InputStream getFileInput(Context context, String name) throws FileNotFoundException {

        return context.openFileInput(name);

    }

    /** Lê o arquivo da memória interna.
     * @param context contexto da classe usuária do método
     * @param fileName nome do arquivo
     * @param  charset charset do arquivo */
    public static String readFile(Context context, String fileName, String charset) throws FileNotFoundException, IOException {

        FileInputStream in = context.openFileInput(fileName);
        return IOUtil.toString(in, charset);

    }

}

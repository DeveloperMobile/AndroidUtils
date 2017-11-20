package com.codigosandroid.utils.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Tiago on 20/11/2017.
 * Salva arquivos no formato chava e valor.
 * Se chamar o método setString("nome", "Tiago"), vai salvar o arquivo
 * /data/data/app/files.nome.txt
 */

public class PrefsFileUtil {

    public static final String PREF_ID = "prefs";
    private static final String TAG = PrefsFileUtil.class.getSimpleName();

    public static void setBoolean(Context context, String chave, boolean on) {
        setString(context, chave, on ? "1" : "0");
    }

    public static boolean getBoolean(Context context, String chave) {
        String s = getString(context, chave);
        boolean on = "1".equals(s);
        return on;
    }

    public static void setInteger(Context context, String chave, int valor) {
        setString(context, chave, String.valueOf(valor));
    }

    public static int getInteger(Context context, String chave) {
        String s = getString(context, chave);
        if (s != null) {
            return Integer.parseInt(s);
        }
        return 0;
    }

    public static void setString(Context context, String chave, String valor) {
        try {
            chave += ".txt";
            File file = context.getFileStreamPath(chave);
            FileOutputStream out = context.openFileOutput(chave, Context.MODE_PRIVATE);
            IOUtil.writeString(out, valor);
            LogUtil.debug(TAG, "PrefsFile.setString file: " + file + " > " + valor);
        } catch (IOException e) {
            LogUtil.error(TAG, e.getMessage(), e);
        }
    }

    public static String getString(Context context, String chave) {
        chave += ".txt";
        File f = context.getFileStreamPath(chave);
        String s = IOUtil.readString(f);
        LogUtil.debug(TAG, "PrefsFile.getString file: " + f + " > " + s);
        return s;
    }

}

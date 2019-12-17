package com.codigosandroid.utils.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by Tiago on 20/11/2017.
 */

public class PrefsSDCardUtil {
    public static final String PREF_ID = "prefs";
    private static final String TAG = PrefsSDCardUtil.class.getSimpleName();

    public static void setBoolean(Context context, String chave, boolean on) {
        setString(context, chave, on ? "1" : "0");
    }

    public static boolean getBoolean(Context context, String chave) {
        String s = getString(context, chave);
        return "1".equals(s);
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
        File f = SDCardUtil.getPublicFile("prefs", chave + ".txt");
        IOUtil.writeString(f, valor);
    }

    public static String getString(Context context, String chave) {
        File f = SDCardUtil.getPublicFile("prefs", chave + ".txt");
        LogUtil.debug(TAG, "getString: " + f);
        return IOUtil.readString(f);
    }
}

package com.codigosandroid.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tiago on 20/11/2017.
 * Encapsula o acesso ao SharedPreferences
 */

public class PrefsUtil {

    public static final String PREF_ID = "utils";

    public static void setBoolean(Context context, String flag, boolean on) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(flag, on);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getBoolean(flag, false);
    }

    public static void setInteger(Context context, String flag, int valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(flag, valor);
        editor.apply();
    }

    public static int getInteger(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getInt(flag, 0);
    }

    public static void setString(Context context, String flag, String valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(flag, valor);
        editor.apply();
    }

    public static String getString(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getString(flag, "");
    }

}

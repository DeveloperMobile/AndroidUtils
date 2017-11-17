package com.codigosandroid.utils.utils;

import android.util.Log;

import com.codigosandroid.utils.R;

/**
 * Created by Tiago on 17/11/2017.
 */

public class LogUtil {

    // Debug
    public static void debug(String tag, String log) {

        Log.d(tag, log);

    }

    // Erro
    public static void error(String tag, String log) {

        Log.e(tag, log);

    }

    // Erro (informa o objeto Exception)
    public static void error(String tag, String log, Throwable e) {

        Log.e(tag, log, e);

    }

    // Informativo
    public static void info(String tag, String log) {

        Log.i(tag, log);

    }

    // Detalhado
    public static void verbose(String tag, String log) {

        Log.v(tag, log);

    }

    // Advertencia
    public static void warn(String tag, String log) {

        Log.w(tag, log);

    }

}

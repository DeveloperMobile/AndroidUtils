package com.codigosandroid.utils.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Created by Tiago on 17/11/2017.
 */

public class AndroidUtil {

    /**
     * É necessária a permissão
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     * @param context contexto da classe usuária do método
     */
    public static boolean isNetworkAvaliable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Retorna true se a versão do android for maior ou igual a 8(Oreo) */
    public static boolean isAndroid8Oreo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    /* Retorna true se a versão do android for maior ou igual a 7(Nougat) */
    public static boolean isAndroid7Nougat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    /* Retorna true se a versão do android for maior ou igual a 5(Lollipop) */
    public static boolean isAndroid6Marshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /* Retorna true se a versão do android for maior ou igual a 5(Lollipop) */
    public static boolean isAndroid5Lollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /* Retorna true se a versão do android for igual a 4.4(Kitkat) */
    public static boolean isAndroid44Kitkat() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT;
    }

    /**  Lê a versão do app
     * @param context contexto da classe usuária do método */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String versionName;

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "N/A";
        }
        return versionName;
    }

    /** Retorna o número de série do OS
     * @param context contexto da classe usuária do método */
    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /** Retorna o imei do dispositivo
     * @param context contexto da classe usuária do método
     *  É necessária a permissão
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"*/
    public static String getImei(Context context) throws SecurityException, NullPointerException {
        String imei;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (isAndroid8Oreo()) {
            imei = telephonyManager.getMeid();
        } else {
            imei = telephonyManager.getDeviceId();
        }
        return imei;
    }

    /** Verifica se o dispositivo possui imei, se não utiliza o android_id
     * @param context contexto da classe usuária do método */
    public static String getSerial(Context context) {
        String serial;
        if (AndroidUtil.getImei(context) == null) {
            serial = AndroidUtil.getAndroidId(context);
        } else {
            serial = AndroidUtil.getImei(context);
        }
        return serial;
    }

    /**Retorna se a tela é large ou xlarge
     * @param context contexto da classe usuária do método */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}

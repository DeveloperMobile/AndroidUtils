package com.codigosandroid.utils.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Tiago on 20/11/2017.
 */

public class SDCardUtil {

    private static final String TAG = SDCardUtil.class.getSimpleName();

    /**
     * Cria um arquivo público na raiz do sdcard
     */
    public static File getPuclicFile(String fileName) {
        File sdCardDir = Environment.getExternalStorageDirectory();
        return createFile(sdCardDir, fileName);
    }

    /** Cria um arquivo público na raiz do sdcard
     * @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS,
     * DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS ou DIRECTORY_DCIM */
    public static File getPublicFile(String fileName, String type) {
        File sdCardDir = Environment.getExternalStoragePublicDirectory(type);
        return createFile(sdCardDir, fileName);
    }

    /** Cria um arquivo privado na raiz do sdcard
     * @param  context
     * @param  fileName
     * @return */
    public static File getPrivateFile(Context context, String fileName) {
        File sdCardDir = context.getExternalFilesDir(null);
        return createFile(sdCardDir, fileName);
    }

    /** Cria um arquivo privado na raiz do sdcard
     * @param  context
     * @param  fileName
     * @param type     @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS,
     * DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS
     * @return */
    public static File getPrivateFile(Context context, String fileName, String type) {
        File sdCardDir = context.getExternalFilesDir(type);
        return createFile(sdCardDir, fileName);
    }

    /** Cria o arquivo no SDCard na pasta informada.
     * @param sdCardDir Pasta do sdcrad
     * @param fileName Nome do arquivo
     * @return */
    private static File createFile(File sdCardDir, String fileName) {
        if (!sdCardDir.exists()) {
            sdCardDir.mkdir(); // Cria o diretório se não existe
        }
        // Retorna o arquivo para ler ou salvar no sdcard
        File file = new File(sdCardDir, fileName);
        return file;
    }

    /** Retorna a pasta montada no /mnt/sdcard
     * @param context
     * @param  preferedDir
     * @return */
    public static File getSdCardDir(Context context, String preferedDir) {
        File dir = null;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = new File(Environment.getExternalStorageDirectory(), preferedDir);
        } else {
            dir = context.getCacheDir();
        }

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }

    /** Retorna um File do /mnt/sdcard/${folderName}/${fileName}
     * @param context
     * @param folderName
     * @param fileName
     * @return */
    public static File getSdCardFile(Context context, String folderName, String fileName) {
        File sdcard = getSdCardDir(context, folderName);
        File f = new File(sdcard, fileName);
        LogUtil.debug(TAG, "<< getSdCardFile > " + f);
        return f;
    }

}

package com.codigosandroid.utils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 20/11/2017.
 */

public class PermissionUtil {

    /** Sistemas de permissão do Android 6.0
     * http://developer.android.com/preview/features/runtime-permissions.html
     * @param  activity
     * @param requestCode
     * @param permissions */
    public static boolean validade(Activity activity, int requestCode, String... permissions) {
        List<String> list = new ArrayList<>();
        for (String permission : permissions) {
            if (!checkPermission(activity, permission)) {
                list.add(permission);
            }
        }
        if (list.isEmpty()) {
            return true;
        }

        String[] newPermissons = new String[list.size()];
        list.toArray(newPermissons);
        ActivityCompat.requestPermissions(activity, newPermissons, requestCode);
        return false;
    }

    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isGpsPermissonOk(Context context) {
        boolean ok1 = checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        boolean ok2 = checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        return ok1 && ok2;
    }
}

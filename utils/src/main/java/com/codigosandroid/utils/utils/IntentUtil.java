package com.codigosandroid.utils.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.Html;

import java.util.List;

/**
 * Created by Tiago on 20/11/2017.
 */

public class IntentUtil {

    private static final String TAG = IntentUtil.class.getSimpleName();

    public static void openBrowser(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.error(TAG, "openBrowser() - ActivityNotFoundException [\"+url+\"]: ");
        }
    }

    public static void showVideo(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "video/*");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.error(TAG, "showVideo() - ActivityNotFoundException [\"+url+\"]: ");
        }
    }

    public static boolean isAvaliable(Context context, Intent intent) {
        final PackageManager mgr = context.getPackageManager();
        List<ResolveInfo> list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    public static void sendEmail(Context context, String toEmail, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("plain/text"); // message/rfc822
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ toEmail });
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(message));
        context.startActivity(intent);
    }

}

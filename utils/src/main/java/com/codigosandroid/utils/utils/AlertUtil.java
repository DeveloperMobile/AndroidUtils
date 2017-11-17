package com.codigosandroid.utils.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tiago on 17/11/2017.
 */

public class AlertUtil {

    public static void alert(Context context, String message) {

        alert(context, null, message, 0, 0);

    }

    public static void alert(Context context, String title, String message) {

        alert(context, title, message, 0, 0);

    }


    public static void alert(Context context, String title, String message, final int yesButton, int icon) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (icon > 0) {
            builder.setIcon(icon);
        }

        if (title != null) {
            builder.setTitle(title);
        }

        builder.setMessage(message);

        String sim = yesButton > 0 ? context.getString(yesButton) : "OK";

        // Adiciona os botões
        builder.setPositiveButton(sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public static void alert(Context context, String title, String message, final int yesButton, final Runnable runnable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        String sim = yesButton > 0 ? context.getString(yesButton) : "OK";
        // Adiciona os botões
        builder.setPositiveButton(sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public static void alert(Context context, String title, String message, final int yesButton, final int noButton,
                             final Runnable runnable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        String sim = yesButton > 0 ? context.getString(yesButton) : "Sim";
        String nao = noButton > 0 ? context.getString(noButton) : "Não";
        // Adiciona os botões
        builder.setPositiveButton(sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        builder.setNegativeButton(nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public static void alert(Context context, String title, String message, final int yesButton, int noButton,
                             final Runnable yesRunnable, final Runnable noRunnable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        String sim = yesButton > 0 ? context.getString(yesButton) : "Sim";
        String nao = noButton > 0 ? context.getString(noButton) : "Não";
        // Adiciona os botões
        builder.setPositiveButton(sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (yesRunnable != null) {
                    yesRunnable.run();
                }
            }
        });
        builder.setNegativeButton(nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (noRunnable != null) {
                    noRunnable.run();
                }
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}

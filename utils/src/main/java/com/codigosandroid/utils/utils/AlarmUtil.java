package com.codigosandroid.utils.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Tiago on 17/11/2017.
 */

public class AlarmUtil {

    // Intervalo de execução do alarme(2 min)
    public static final long INTERVAL = (60 * 1000) * 2;
    // Tempo longo para o alarme (10 mim)
    public static final long LONG_TIME = (60 * 1000) * 10;
    // Tempo normal para o alarme (2 min)
    public static final long TIME = (60 * 1000) * 2;

    // Agenda o alarme
    public static void alarmBroadcast(Context context, Intent intent, long time) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = (PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        LogUtil.info(AlarmUtil.class.getSimpleName(), "Alargme agendado com sucesso.");
    }

    // Agenda o alarme com repeat
    public static void alarmRepeat(Context context, Intent intent, long triggerAtMillis, long intervalMillis) throws NullPointerException {
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, p);
        LogUtil.info(AlarmUtil.class.getSimpleName(), "Alargme com repeat agendado com sucesso.");
    }

    // Cancela o alarme
    public static void alarmCancel(Context context, Intent intent) throws NullPointerException {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(p);
        LogUtil.info(AlarmUtil.class.getSimpleName(), "Alargme cancelado com sucesso.");
    }

}

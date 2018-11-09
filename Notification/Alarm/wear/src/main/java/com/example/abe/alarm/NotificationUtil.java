package com.example.abe.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Classe responsável por criar uma notificação. Cria o canal em que a intent passará
 * e cria uma notificação que passará por esse canal.
 *
 * Criado por: Bruno Abe
 * */
public class NotificationUtil {
    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";
    public static int SIMPLE_NOTIFICATION_ID = 1;

    /*
     * Cria o canal de comunicação para a intent
     * */
    public static void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Checa se o canal já existe, ou não
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
        }
    }

    /*
    * Constrói uma Intent, colocando um título e um texto na mesma
    * */
    public static Notification createSimpleNotification(Context context, String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(android.R.drawable.ic_menu_view);

        return builder.build();
    }
}

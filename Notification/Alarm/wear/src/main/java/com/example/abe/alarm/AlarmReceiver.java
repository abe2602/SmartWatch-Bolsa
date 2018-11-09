package com.example.abe.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Recebe o pedido de um "alarme" recorrente. É nessa classe onde
 * os pedidos de alarme são guardados e uma notificação é criada na hora
 * e minutos específicados, além de vibrar.
 *
 * Criado por: Bruno Abe
 * */
public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Alarme!!!!", Toast.LENGTH_SHORT).show();
        this.context = context;
        mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationUtil.createNotificationChannel(mNotificationManager);
        Notification notification = NotificationUtil.createSimpleNotification(context, "Lembrete", "Não esqueça do seu remédio!");
        mNotificationManager.notify(NotificationUtil.SIMPLE_NOTIFICATION_ID, notification);

        this.vibrate();
    }

    //Método que realiza a vibração
    public void vibrate(){
        Vibrator vibrator = (Vibrator) this.context.getSystemService(VIBRATOR_SERVICE);
        long[] vibrationPattern = {0, 500, 50, 300};
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
    }
}

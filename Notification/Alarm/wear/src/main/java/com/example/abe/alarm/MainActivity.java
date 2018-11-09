package com.example.abe.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView hourRecycleView = findViewById(R.id.rcHoras);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        hourRecycleView.setLayoutManager(layoutManager);
        AdapterTime hours = new AdapterTime(24);
        hourRecycleView.setAdapter(hours);

        RecyclerView minuteRecyclerView = findViewById(R.id.rcMinutos);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        minuteRecyclerView.setLayoutManager(layoutManager2);
        AdapterTime minutes = new AdapterTime(60);
        minuteRecyclerView.setAdapter(minutes);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("EXECUTAR_ALARME");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                //Instancia o calendário
                Calendar cal = Calendar.getInstance();

                cal.set(Calendar.HOUR_OF_DAY, 20);
                cal.set(Calendar.MINUTE, 15);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.AM_PM, Calendar.PM);

                //Agenda o Alarme
                AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
                long time = cal.getTimeInMillis();
                alarme.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

                Log.i("Alarme", "Alarme agendado!");
            }
        });

        setAmbientEnabled();
    }
}

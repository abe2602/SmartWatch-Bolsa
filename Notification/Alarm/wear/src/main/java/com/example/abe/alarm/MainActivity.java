package com.example.abe.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Atividade principal. Nela temos as definições dos widgets e
 * ações nos botões
 *
 * Criado por: Bruno Abe
 * */
public class MainActivity extends WearableActivity {
    private EditText hours, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hours = findViewById(R.id.editTextHour);
        minutes = findViewById(R.id.editTextMinutes);

        //Ação no botão: Setar um alarme usando AlarmManager
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verifica se a hora digitada é válida
                if(Integer.parseInt(hours.getText().toString()) >= 0 && Integer.parseInt(hours.getText().toString()) < 24
                && Integer.parseInt(minutes.getText().toString()) >= 0 && Integer.parseInt(minutes.getText().toString()) < 60){

                    //Intente a ser executada
                    Intent intent = new Intent("EXECUTAR_ALARME");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                    //Instancia o calendário
                    Calendar cal = Calendar.getInstance();

                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours.getText().toString()));
                    cal.set(Calendar.MINUTE, Integer.parseInt(minutes.getText().toString()));
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.AM_PM, Calendar.PM);

                    //Agenda o Alarme
                    AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
                    long time = cal.getTimeInMillis();
                    alarme.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

                    Toast.makeText(getApplicationContext(), "Alarme agendado!", Toast.LENGTH_SHORT).show();
                    Log.i("Alarme", "Alarme agendado!");
                }else{
                    Toast.makeText(getApplicationContext(), "Hora inválida!", Toast.LENGTH_SHORT).show();
                }
                Log.i("Alarme", "Algo deu ruim!");
            }
        });

        setAmbientEnabled();
    }
}

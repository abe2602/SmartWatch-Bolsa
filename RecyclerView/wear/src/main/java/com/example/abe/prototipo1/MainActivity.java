package com.example.abe.prototipo1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends WearableActivity {
    private WearableRecyclerView recyclerView;
    private Adapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // To align the edge children (first and last) with the center of the screen
        this.recyclerView = findViewById(R.id.recycler_launcher_view);
        this.recyclerViewAdapter = new Adapter(100);

        this.recyclerView.setEdgeItemsCenteringEnabled(true);
        this.recyclerView.setEdgeItemsCenteringEnabled(true);
        this.recyclerView.setLayoutManager(new WearableLinearLayoutManager(this));
        this.recyclerView.setAdapter(recyclerViewAdapter);
        // Enables Always-on
        setAmbientEnabled();

    }

    //Nosso SmartWatch não possui Speakers :(
    public boolean hasSpeaker(){
        PackageManager packageManager = this.getPackageManager();
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // Check whether the device has a speaker.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                // Check FEATURE_AUDIO_OUTPUT to guard against false positives.
                packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
            AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
            for (AudioDeviceInfo device : devices) {
                if (device.getType() == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER) {
                    return true;
                }
            }
        }
        return false;
    }

    //Possui vibração :)
    public void vibrate(){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] vibrationPattern = {0, 500, 50, 300};
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
    }
}

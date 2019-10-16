package com.example.harrywiner.accelerometertest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  implements SensorEventListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        Log.i("")
        if (sensorEvent.values[1] != 0 || sensorEvent.values[0] != 0)
        {

            TextView textView = findViewById(R.id.textView1);
            textView.setText("Shaken");

        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int x){}
}

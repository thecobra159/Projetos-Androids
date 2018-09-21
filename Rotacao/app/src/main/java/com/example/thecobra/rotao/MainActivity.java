package com.example.thecobra.rotao;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView textView;
    private SensorEventListener sensorEventListener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtSensores);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor;
        if (sensorManager != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(this, sensor, 10000);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText("");
        for (float value : event.values){
            textView.append(Float.toString(value) + "\n");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}

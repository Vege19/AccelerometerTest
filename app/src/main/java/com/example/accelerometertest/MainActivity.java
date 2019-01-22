package com.example.accelerometertest;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private static ImageView arrow;
    private TextView xValue, yValue;
    private RelativeLayout layout;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        arrow = findViewById(R.id.arrow);
        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        layout = findViewById(R.id.layout);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //sensor manager setup
        sensorManager = (SensorManager) this.getSystemService(this.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //sensor activity
        Sensor sensor = event.sensor;

        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:

                //directions
                float x = event.values[0];
                float y = event.values[1];

                //sensor behavior
                xValue.setText("x: " + String.valueOf(x));
                yValue.setText("y: " + String.valueOf(y));

                //rotate arrow with accelerometer
                if (x < arrow.getRotation()) {
                    arrow.setRotation(arrow.getRotation() - x);
                    layout.setBackgroundColor(Color.BLUE);

                } else if (x > arrow.getRotation()) {
                    arrow.setRotation(arrow.getRotation() + -x);
                    layout.setBackgroundColor(Color.GREEN);

                }

                break;
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

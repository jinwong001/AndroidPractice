package com.jin.androiduipractice.weatherCurve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jin.androiduipractice.R;

public class WeatherCurveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_curve);
        TextView textView = (TextView) findViewById(R.id.tv);
    }
}

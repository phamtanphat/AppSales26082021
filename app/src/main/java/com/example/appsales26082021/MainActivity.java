package com.example.appsales26082021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarComponent carComponent = DaggerCarComponent.builder()
                .bindEngine(new Engine(10, "Best Engine 1"))
                .bindWheel(new Wheel(10))
                .build();
        carComponent.inject(this);

        car.drive();
    }
}
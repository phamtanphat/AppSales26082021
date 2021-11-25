package com.example.appsales26082021;

import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

public class MyApplication extends Application {

    @Inject
    Car car;

    @Override
    public void onCreate() {
        super.onCreate();

        CarComponent carComponent = DaggerCarComponent.builder()
                .bindWheel(new Wheel(4))
                .bindEngine(new Engine(4, "Best Engine"))
                .application(this)
                .build();
        carComponent.inject(this);
        Log.d("BBB",car.toString());

    }
}

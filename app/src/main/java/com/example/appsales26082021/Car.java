package com.example.appsales26082021;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private Wheel wheel;
    private Engine engine;

    @Inject
    public Car(Wheel wheel, Engine engine) {
        this.wheel = wheel;
        this.engine = engine;
    }

    public void drive(){
        Log.d("BBB","Car can to go every where");
        Log.d("BBB","Wheel " + wheel.toString());
        Log.d("BBB","Engine " + engine.toString());
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}

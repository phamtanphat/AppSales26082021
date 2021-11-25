package com.example.appsales26082021;

import android.app.Application;
import android.graphics.drawable.Drawable;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {

    @Provides
    Drawable getDrawable(Application application) {
        return application.getDrawable(R.drawable.hinh1);
    }

    @Provides
    String getStringCar(Engine engine, Wheel wheel){
        return engine.toString() + "\n" + wheel.toString();
    }
}

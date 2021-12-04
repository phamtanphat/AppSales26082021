package com.example.appsales26082021;

import com.example.appsales26082021.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MyApplication extends DaggerApplication {

    DaggerAppComponent daggerAppComponent;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return daggerAppComponent.builder().application(this).build();
    }
}

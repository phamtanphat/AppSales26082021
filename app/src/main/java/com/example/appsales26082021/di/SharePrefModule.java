package com.example.appsales26082021.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.appsales26082021.util.Constant;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharePrefModule {

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences(Constant.KEY_NAME_SHARE_PREF,Context.MODE_PRIVATE);
    }

}

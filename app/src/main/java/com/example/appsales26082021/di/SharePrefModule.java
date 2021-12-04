package com.example.appsales26082021.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.util.SharePref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharePrefModule {

    @Singleton
    @Provides
    public SharedPreferences providerSharePreference(Application application){
        return application.getSharedPreferences(Constant.KEY_NAME_SHARE_PREF, Context.MODE_PRIVATE);
    }

}

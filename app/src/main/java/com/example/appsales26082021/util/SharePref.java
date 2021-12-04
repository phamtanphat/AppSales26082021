package com.example.appsales26082021.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharePref {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Inject
    public SharePref(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

}

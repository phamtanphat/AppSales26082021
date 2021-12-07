package com.example.appsales26082021.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharePref {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Inject
    public SharePref(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = this.sharedPreferences.edit();
    }

    public void setToken(String key, String token) {
        editor.putString(key, token);
        editor.apply();
    }

    public String getToken(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void clearData() {
        editor.clear();
        editor.apply();
    }
}

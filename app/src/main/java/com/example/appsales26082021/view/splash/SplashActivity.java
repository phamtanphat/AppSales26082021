package com.example.appsales26082021.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appsales26082021.R;

import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
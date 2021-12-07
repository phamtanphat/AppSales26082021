package com.example.appsales26082021.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appsales26082021.R;
import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.util.SharePref;
import com.example.appsales26082021.view.main.MainActivity;
import com.example.appsales26082021.view.sign_in.SignInActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    SharePref sharePref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = sharePref.getToken(Constant.KEY_TOKEN);
                if (token.isEmpty()){
                    startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        },2000);
    }
}
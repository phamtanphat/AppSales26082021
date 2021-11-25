package com.example.appsales26082021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.DaggerActivity;
import retrofit2.Retrofit;

public class MainActivity2 extends DaggerActivity {

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Log.d("BBB",retrofit.toString());
    }
}
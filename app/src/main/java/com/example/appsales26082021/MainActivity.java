package com.example.appsales26082021;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import dagger.android.DaggerActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends DaggerActivity {

    @Inject
    RequestApi requestApi;

    @Inject
    Retrofit retrofit;

    Button mBtnMain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnMain2 = findViewById(R.id.buttonMain2);

        Log.d("BBB",retrofit.toString());


        Call<Demo1> calldemo1 = requestApi.callDemo1();
        calldemo1.enqueue(new Callback<Demo1>() {
            @Override
            public void onResponse(Call<Demo1> call, Response<Demo1> response) {
                Demo1 demo1 = response.body();
                Log.d("BBB",demo1.toString());
            }

            @Override
            public void onFailure(Call<Demo1> call, Throwable t) {

            }
        });

        mBtnMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });



    }
}
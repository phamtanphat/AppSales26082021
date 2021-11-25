package com.example.appsales26082021;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    Car car;

    ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImg = findViewById(R.id.imageView);

        car = ((MyApplication) getApplication()).car;

        Log.d("BBB",car.toString());

//        car.drive();
    }
}
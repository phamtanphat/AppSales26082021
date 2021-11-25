package com.example.appsales26082021;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Car car;

    @Inject
    String stringFromObject;

    @Inject
    Drawable drawableFromModule;

    ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImg = findViewById(R.id.imageView);

        CarComponent carComponent = DaggerCarComponent.builder()
                .bindWheel(new Wheel(4))
                .bindEngine(new Engine(4, "Best Engine"))
                .application(getApplication())
                .build();
        carComponent.inject(this);

        Log.d("BBB",stringFromObject);

        mImg.setImageDrawable(drawableFromModule);

//        car.drive();
    }
}
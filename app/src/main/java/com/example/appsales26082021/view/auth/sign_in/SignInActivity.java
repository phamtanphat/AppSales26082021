package com.example.appsales26082021.view.auth.sign_in;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.appsales26082021.R;
import com.example.appsales26082021.view.main.MainActivity;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;
import com.example.appsales26082021.view.auth.AuthViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignInActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelFactoryProvider provider;
//
    AuthViewModel authViewModel;

    Button mBtnNavigateMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        authViewModel = new ViewModelProvider(this,provider).get(AuthViewModel.class);

        mBtnNavigateMain = findViewById(R.id.buttonNavigateMain);

        Log.d("BBB","Sign In Provider " + provider.toString());

        mBtnNavigateMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }
        });

    }
}
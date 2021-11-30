package com.example.appsales26082021.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.appsales26082021.R;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;
import com.example.appsales26082021.di.viewmodel.auth.AuthViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignInActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelFactoryProvider provider;
//
    AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        authViewModel = new ViewModelProvider(this,provider).get(AuthViewModel.class);

    }
}
package com.example.appsales26082021.view.main;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.R;
import com.example.appsales26082021.view.auth.AuthViewModel;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelFactoryProvider provider;
    //
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this,provider).get(MainViewModel.class);

        Log.d("BBB","Main Provider " + provider.toString());
    }
}
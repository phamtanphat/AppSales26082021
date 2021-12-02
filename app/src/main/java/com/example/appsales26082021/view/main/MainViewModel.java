package com.example.appsales26082021.view.main;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    @Inject
    public MainViewModel() {
        Log.d("BBB", "Main View Model init");
    }
}

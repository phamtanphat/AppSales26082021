package com.example.appsales26082021.di.viewmodel;

import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelFactoryModule {

    @Singleton
    @Provides
    public ViewModelProvider.Factory bindViewModelFactory(ViewModelFactoryProvider viewModelFactory){
        return viewModelFactory;
    }

}
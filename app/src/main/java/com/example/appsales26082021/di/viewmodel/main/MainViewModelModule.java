package com.example.appsales26082021.di.viewmodel.main;

import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.di.ViewModelKey;
import com.example.appsales26082021.viewmodel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract public ViewModel bindMainViewModel(MainViewModel authViewModel);
}

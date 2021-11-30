package com.example.appsales26082021.di.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.di.ViewModelKey;
import com.example.appsales26082021.di.viewmodel.auth.AuthViewModel;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    abstract public ViewModel bindAuthViewModel(AuthViewModel authViewModel);

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactoryProvider viewModelFactory);

}
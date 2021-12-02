package com.example.appsales26082021.di.viewmodel.auth;

import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.di.ViewModelKey;
import com.example.appsales26082021.viewmodel.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}

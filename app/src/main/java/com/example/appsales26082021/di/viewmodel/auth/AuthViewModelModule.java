package com.example.appsales26082021.di.viewmodel.auth;

import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.di.ViewModelKey;
import com.example.appsales26082021.view.auth.AuthViewModel;
import com.example.appsales26082021.view.main.MainViewModel;

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

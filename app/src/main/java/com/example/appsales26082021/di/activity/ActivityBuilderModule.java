package com.example.appsales26082021.di.activity;

import com.example.appsales26082021.di.viewmodel.auth.AuthViewModelModule;
import com.example.appsales26082021.di.viewmodel.main.MainViewModelModule;
import com.example.appsales26082021.view.main.MainActivity;
import com.example.appsales26082021.view.sign_in.SignInActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {MainViewModelModule.class}
    )
    abstract public MainActivity bindContributeMainActivity();

    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class}
    )
    abstract public SignInActivity bindContributeSignInActivity();
}

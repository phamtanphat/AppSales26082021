package com.example.appsales26082021.di.activity;

import com.example.appsales26082021.view.MainActivity;
import com.example.appsales26082021.view.SignInActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract public MainActivity bindContributeMainActivity();

    @ContributesAndroidInjector
    abstract public SignInActivity bindContributeSignInActivity();
}

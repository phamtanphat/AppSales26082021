package com.example.appsales26082021.di;

import com.example.appsales26082021.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract public MainActivity bindContributeMainActivity();
}

package com.example.appsales26082021;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract public MainActivity bindBuilderContributedMainActivity();

    @ContributesAndroidInjector
    abstract public MainActivity2 bindBuilderContributedMainActivity2();
}

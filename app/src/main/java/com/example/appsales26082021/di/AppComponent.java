package com.example.appsales26082021.di;

import com.example.appsales26082021.MyApplication;
import com.example.appsales26082021.di.activity.ActivityBuilderModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {

}

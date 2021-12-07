package com.example.appsales26082021.di;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.appsales26082021.MyApplication;
import com.example.appsales26082021.di.activity.ActivityBuilderModule;
import com.example.appsales26082021.di.viewmodel.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
                SharePrefModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {

    SharedPreferences getSharePreference();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

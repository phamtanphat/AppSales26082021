package com.example.appsales26082021.di;

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
                ViewModelFactoryModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApplication myApplication);

        AppComponent build();
    }
}

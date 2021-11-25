package com.example.appsales26082021;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
        modules = {CarModule.class}
)
public interface CarComponent {

    Car getCar();

    void inject(MyApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder bindEngine(Engine engine);

        @BindsInstance
        Builder bindWheel(Wheel wheel);

        CarComponent build();
    }
}

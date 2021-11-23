package com.example.appsales26082021;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;

@Component
public interface CarComponent {

    Car getCar();

    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder bindEngine(Engine engine);

        @BindsInstance
        Builder bindWheel(Wheel wheel);

        CarComponent build();
    }
}

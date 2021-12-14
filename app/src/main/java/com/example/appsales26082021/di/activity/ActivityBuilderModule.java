package com.example.appsales26082021.di.activity;

import com.example.appsales26082021.di.viewmodel.auth.AuthViewModelModule;
import com.example.appsales26082021.di.viewmodel.cart.CartViewModelModule;
import com.example.appsales26082021.di.viewmodel.main.MainViewModelModule;
import com.example.appsales26082021.view.cart.CartActivity;
import com.example.appsales26082021.view.main.MainActivity;
import com.example.appsales26082021.view.sign_in.SignInActivity;
import com.example.appsales26082021.view.sign_up.SignUpActivity;
import com.example.appsales26082021.view.splash.SplashActivity;
import com.example.appsales26082021.viewmodel.CartViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
            modules = {CartViewModelModule.class}
    )
    abstract public CartActivity bindContributeCartActivity();

    @ContributesAndroidInjector(
            modules = {MainViewModelModule.class}
    )
    abstract public MainActivity bindContributeMainActivity();

    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class}
    )
    abstract public SignInActivity bindContributeSignInActivity();

    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class}
    )
    abstract public SignUpActivity bindContributeSignUpActivity();

    @ContributesAndroidInjector
    abstract public SplashActivity bindContributeSplashActivity();
}

package com.example.appsales26082021.di.viewmodel.cart;

import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.di.ViewModelKey;
import com.example.appsales26082021.viewmodel.CartViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CartViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    public abstract ViewModel provideCartViewModel(CartViewModel cartViewModel);
}

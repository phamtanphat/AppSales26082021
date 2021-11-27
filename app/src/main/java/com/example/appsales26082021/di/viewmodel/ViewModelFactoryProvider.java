package com.example.appsales26082021.di.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactoryProvider implements ViewModelProvider.Factory {
    private Map<Class<? extends ViewModel> , Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactoryProvider(Map<Class<? extends ViewModel> , Provider<ViewModel>> creators){
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modeClass) {
        Provider<? extends ViewModel> creator = creators.get(modeClass);
        if (creator == null){
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modeClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        if (creator == null){
            throw new IllegalArgumentException("Unknow viewmodel");
        }
        return (T) creator.get();
    }
}

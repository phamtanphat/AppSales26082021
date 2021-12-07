package com.example.appsales26082021.view.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.R;
import com.example.appsales26082021.adapter.FoodAdapter;
import com.example.appsales26082021.databinding.ActivityMainBinding;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.viewmodel.MainViewModel;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelFactoryProvider provider;
    //
    MainViewModel mainViewModel;

    private FoodAdapter mFoodAdapter;
    private List<FoodModel> mListFoods;
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mainViewModel = new ViewModelProvider(this,provider).get(MainViewModel.class);

        initData();
        observerData();
        event();
    }

    private void event() {

    }

    private void observerData() {

    }

    private void initData() {
        mListFoods = new ArrayList<>();
        mFoodAdapter = new FoodAdapter();
        mBinding.recyclerViewMain.setHasFixedSize(true);
        mBinding.recyclerViewMain.setAdapter(mFoodAdapter);
    }
}
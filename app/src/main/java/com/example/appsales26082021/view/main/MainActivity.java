package com.example.appsales26082021.view.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.R;
import com.example.appsales26082021.adapter.FoodAdapter;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.databinding.ActivityMainBinding;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.util.SharePref;
import com.example.appsales26082021.view.sign_in.SignInActivity;
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
    private List<FoodModel> mListCarts;

    private ActivityMainBinding mBinding;


    @Inject
    SharePref sharePref;
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
        mainViewModel.fetchListFoods();
        mainViewModel.fetchCart();
    }

    private void observerData() {
        mainViewModel.getListFoods().observe(this, new Observer<ResourceType<List<FoodModel>>>() {
            @Override
            public void onChanged(ResourceType<List<FoodModel>> listResourceType) {
                if (listResourceType.status == ResourceType.Status.LOADING) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else if (listResourceType.status == ResourceType.Status.SUCCESS) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                    mListFoods = listResourceType.data;
                    mFoodAdapter.updateListFoodModels(mListFoods);
                } else {
                    Toast.makeText(MainActivity.this, listResourceType.message, Toast.LENGTH_SHORT).show();
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                }
            }
        });

        mainViewModel.getCart().observe(this, new Observer<ResourceType<List<FoodModel>>>() {
            @Override
            public void onChanged(ResourceType<List<FoodModel>> listResourceType) {
                if (listResourceType.status == ResourceType.Status.LOADING) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else if (listResourceType.status == ResourceType.Status.SUCCESS) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                    mListCarts = listResourceType.data;
                } else {
                    Toast.makeText(MainActivity.this, listResourceType.message, Toast.LENGTH_SHORT).show();
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initData() {
        mListFoods = new ArrayList<>();
        mFoodAdapter = new FoodAdapter();
        mBinding.recyclerViewMain.setHasFixedSize(true);
        mBinding.recyclerViewMain.setAdapter(mFoodAdapter);

        mBinding.toolbarMain.setTitle("Home");
        mBinding.toolbarMain.setTitleTextColor(Color.WHITE);
        mBinding.toolbarMain.setNavigationIcon(R.drawable.ic_person);
        setSupportActionBar(mBinding.toolbarMain);



        mBinding.toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePref.clearData();
                startActivity(new Intent(MainActivity.this,SignInActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();

        TextView txtCountCart = actionView.findViewById(R.id.textViewCart);

        setBadgeCart(txtCountCart);

        return true;

    }

    private void setBadgeCart(TextView txtCountCart) {
        if (mListCarts == null || mListCarts.size() <= 0){
            txtCountCart.setVisibility(View.GONE);
        }else{
            txtCountCart.setVisibility(View.VISIBLE);
            txtCountCart.setText(mListCarts.size() + "");
        }
    }
}
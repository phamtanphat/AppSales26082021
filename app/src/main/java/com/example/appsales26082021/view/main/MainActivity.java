package com.example.appsales26082021.view.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.R;
import com.example.appsales26082021.adapter.FoodAdapter;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.databinding.ActivityMainBinding;
import com.example.appsales26082021.interfaces.OnFoodItemListener;
import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.model.OrderModel;
import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.util.SharePref;
import com.example.appsales26082021.view.cart.CartActivity;
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
    @Inject
    SharePref mSharePref;

    MainViewModel mainViewModel;

    private FoodAdapter mFoodAdapter;
    private List<FoodModel> mListFoods;
    private CartModel mCartModel;
    private OrderModel mOrderModel;
    private int REQUEST_CODE_CART = 1;


    private ActivityMainBinding mBinding;

    TextView mTxtCountCart;

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
        mainViewModel.fetchTotalCart();

        mFoodAdapter.setOnFoodItemListener(new OnFoodItemListener() {
            @Override
            public void onClick(int position) {
                mainViewModel.addCart(mListFoods.get(position).foodId);
            }
        });
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

        mainViewModel.getCart().observe(this, new Observer<ResourceType<CartModel>>() {
            @Override
            public void onChanged(ResourceType<CartModel> listResourceType) {
                if (listResourceType.status == ResourceType.Status.LOADING) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else if (listResourceType.status == ResourceType.Status.SUCCESS) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                    mCartModel = listResourceType.data;
                    setBadgeCart();
                } else {
                    String code = listResourceType.message.substring(0,3);
                    if (code.equals("401")){
                        Toast.makeText(MainActivity.this, "Phiên làm việc hết hạn", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,SignInActivity.class));
                        mSharePref.clearData();
                        finish();
                        return;
                    }
                    Toast.makeText(MainActivity.this, listResourceType.message, Toast.LENGTH_SHORT).show();
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                }
            }
        });

        mainViewModel.getOrder().observe(this, new Observer<ResourceType<OrderModel>>() {
            @Override
            public void onChanged(ResourceType<OrderModel> orderModelResourceType) {
                if (orderModelResourceType.status == ResourceType.Status.LOADING) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else if (orderModelResourceType.status == ResourceType.Status.SUCCESS) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                    mOrderModel = orderModelResourceType.data;
                    mainViewModel.fetchCart();
                } else {
                    String code = orderModelResourceType.message.substring(0,3);
                    if (code.equals("401")){
                        Toast.makeText(MainActivity.this, "Phiên làm việc hết hạn", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,SignInActivity.class));
                        mSharePref.clearData();
                        finish();
                        return;
                    }
                    if (code.equals("404")){
                        mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                        return;
                    }
                    Toast.makeText(MainActivity.this, orderModelResourceType.message, Toast.LENGTH_SHORT).show();
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
                mSharePref.clearData();
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

        mTxtCountCart = actionView.findViewById(R.id.textViewCart);

        setBadgeCart();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                if (mCartModel != null && mOrderModel != null){
                    intent.putExtra(Constant.KEY_CART,mCartModel);
                    intent.putExtra(Constant.KEY_ORDERID,mOrderModel.orderId);
                }
                startActivityForResult(intent,REQUEST_CODE_CART);
            }
        });

        return true;

    }

    private void setBadgeCart() {
        if (mCartModel == null || mCartModel.items == null || mCartModel.items.size() <= 0){
            mTxtCountCart.setVisibility(View.GONE);
        }else{
            mTxtCountCart.setVisibility(View.VISIBLE);
            int count = 0;
            for (FoodModel foodModel : mCartModel.items) {
                count += foodModel.quantity;
            }
            mTxtCountCart.setText(count + "");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CART && resultCode == RESULT_OK && data != null){
            CartModel dataCart = (CartModel) data.getSerializableExtra(Constant.KEY_CART);
            mCartModel = dataCart;
            setBadgeCart();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
package com.example.appsales26082021.view.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appsales26082021.R;
import com.example.appsales26082021.adapter.CartAdapter;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.databinding.ActivityCartBinding;
import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.model.OrderModel;
import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.util.Helper;
import com.example.appsales26082021.view.main.MainActivity;
import com.example.appsales26082021.view.sign_in.SignInActivity;
import com.example.appsales26082021.viewmodel.CartViewModel;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class CartActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelFactoryProvider provider;

    private CartViewModel mCartViewModel;
    private ActivityCartBinding mBinding;
    private CartModel mCartModel;
    private String mOrderId;
    private CartAdapter mCartAdapter;
    private List<FoodModel> mListFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initData();
        initView();
        observerData();
        event();

    }

    private void event() {
        mBinding.toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCartAdapter.setOnListenerCartItem(new CartAdapter.OnListenerCartItem() {
            @Override
            public void onPlus(int position) {
                mCartViewModel.updateCart(mOrderId, mListFoods.get(position).foodId, mListFoods.get(position).quantity + 1);
            }

            @Override
            public void onMinus(int position) {
                if (mListFoods.get(position).quantity > 1) {
                    mCartViewModel.updateCart(mOrderId, mListFoods.get(position).foodId, mListFoods.get(position).quantity - 1);
                }

            }

            @Override
            public void onDelete(int position) {
                mCartViewModel.deleteItemCart(mListFoods.get(position).foodId);
            }
        });
    }

    private void observerData() {
        mCartViewModel.getDataUpdate().observe(this, new Observer<ResourceType<String>>() {
            @Override
            public void onChanged(ResourceType<String> stringResourceType) {
                if (stringResourceType.status == ResourceType.Status.LOADING) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else if (stringResourceType.status == ResourceType.Status.SUCCESS) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                    mCartViewModel.fetchCart();
                } else {
                    Toast.makeText(CartActivity.this, stringResourceType.message, Toast.LENGTH_SHORT).show();
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                }
            }
        });

        mCartViewModel.getDataCart().observe(this, new Observer<ResourceType<CartModel>>() {
            @Override
            public void onChanged(ResourceType<CartModel> cartModelResourceType) {
                if (cartModelResourceType.status == ResourceType.Status.LOADING) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else if (cartModelResourceType.status == ResourceType.Status.SUCCESS) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                    mCartModel = cartModelResourceType.data;
                    mListFoods = mCartModel.items;
                    mCartAdapter.updateCart(mListFoods);
                    setTextTotal();
                } else {
                    Toast.makeText(CartActivity.this, cartModelResourceType.message, Toast.LENGTH_SHORT).show();
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initView() {
        mBinding.toolbarCart.setTitle("Cart");
        mBinding.toolbarCart.setTitleTextColor(Color.WHITE);
        mBinding.toolbarCart.setNavigationIcon(R.drawable.ic_back);
        mCartAdapter = new CartAdapter();
        mCartAdapter.updateCart(mListFoods);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setAdapter(mCartAdapter);
    }

    private void initData() {
        // init list cart
        mListFoods = new ArrayList<>();
        // Get data from intent MainActivity
        Intent intent = getIntent();
        if (intent != null) {
            mCartModel = (CartModel) intent.getSerializableExtra(Constant.KEY_CART);
            mOrderId = (String) intent.getSerializableExtra(Constant.KEY_ORDERID);
            mListFoods = mCartModel.items;
            setTextTotal();
        }

        mCartViewModel = new ViewModelProvider(this, provider).get(CartViewModel.class);
    }

    private void setTextTotal(){
        mBinding.textviewTotalAmount.setText("Tổng tiền: " + Helper.formatPrice(mCartModel.total) + " VNĐ");
    }
}
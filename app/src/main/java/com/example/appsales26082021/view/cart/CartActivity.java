package com.example.appsales26082021.view.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.appsales26082021.R;
import com.example.appsales26082021.adapter.CartAdapter;
import com.example.appsales26082021.databinding.ActivityCartBinding;
import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.util.Helper;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding mBinding;
    private CartModel mCartModel;
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
    }

    private void observerData() {

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
        if (intent != null){
            mCartModel = (CartModel) intent.getSerializableExtra(Constant.KEY_CART);
            mListFoods = mCartModel.items;
            mBinding.textviewTotalAmount.setText("Tổng tiền: "+ Helper.formatPrice(mCartModel.total) + " VNĐ");
        }
    }
}
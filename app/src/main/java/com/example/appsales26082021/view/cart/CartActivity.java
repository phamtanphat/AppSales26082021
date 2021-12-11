package com.example.appsales26082021.view.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appsales26082021.adapter.CartAdapter;
import com.example.appsales26082021.databinding.ActivityCartBinding;
import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.util.Constant;

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

    }

    private void initView() {
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
        }

    }
}
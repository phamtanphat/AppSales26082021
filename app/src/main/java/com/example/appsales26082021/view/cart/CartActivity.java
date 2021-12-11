package com.example.appsales26082021.view.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.appsales26082021.R;
import com.example.appsales26082021.databinding.ActivityCartBinding;
import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.util.Constant;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        if (intent != null){
            CartModel cartModel = (CartModel) intent.getSerializableExtra(Constant.KEY_CART);
            Log.d("BBB",cartModel.toString());
        }
    }
}
package com.example.appsales26082021.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appsales26082021.databinding.ItemCartBinding;
import com.example.appsales26082021.model.FoodModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private List<FoodModel> lstFoodModel;

    public CartAdapter(){

    }

    public void updateListOrderedItemModels(List<FoodModel> lstFoodModel){
        lstFoodModel = lstFoodModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(layoutInflater,parent,false);
        return new CartViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ((CartViewHolder)holder).bind(holder.itemView,lstFoodModel.get(position));
    }

    @Override
    public int getItemCount() {
        return lstFoodModel.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        //Binding
        ItemCartBinding mBinding;

        public CartViewHolder(ItemCartBinding itemCartBinding){
            super(itemCartBinding.getRoot());
            mBinding = itemCartBinding;

        }
        void bind(View itemView, FoodModel foodModel){
            //Image
            Glide.with(itemView)
                    .load(foodModel.images.get(0).imageUrl)
                    .into(mBinding.imageView);
            mBinding.textViewName.setText(foodModel.foodName);
            mBinding.textViewPrice.setText(foodModel.price+"");
            mBinding.textviewQuantity.setText(foodModel.quantity+"");


        }
    }

}


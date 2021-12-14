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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<FoodModel> lstFoodModel;
    private OnListenerCartItem onListenerCartItem;

    public CartAdapter() {

    }

    public void updateCart(List<FoodModel> lstFoodModel) {
        this.lstFoodModel = lstFoodModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(layoutInflater, parent, false);
        return new CartViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ((CartViewHolder) holder).bind(holder.itemView, lstFoodModel.get(position));
    }

    @Override
    public int getItemCount() {
        return lstFoodModel == null ? 0 : lstFoodModel.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        //Binding
        ItemCartBinding mBinding;

        public CartViewHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            mBinding = itemCartBinding;
            mBinding.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onListenerCartItem != null) {
                        onListenerCartItem.onMinus(getAdapterPosition());
                    }
                }
            });
            mBinding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onListenerCartItem != null) {
                        onListenerCartItem.onPlus(getAdapterPosition());
                    }
                }
            });

        }

        void bind(View itemView, FoodModel foodModel) {
            //Image
            Glide.with(itemView)
                    .load(foodModel.images.get(0).imageUrl)
                    .into(mBinding.imageView);
            mBinding.textViewName.setText(foodModel.foodName);
            mBinding.textViewPrice.setText(foodModel.price + "");
            mBinding.textviewQuantity.setText(foodModel.quantity + "");
        }
    }

    public void setOnListenerCartItem(OnListenerCartItem onListenerCartItem) {
        this.onListenerCartItem = onListenerCartItem;
    }

    public interface OnListenerCartItem {
        void onPlus(int position);

        void onMinus(int position);

        void onDelete(int position);
    }


}


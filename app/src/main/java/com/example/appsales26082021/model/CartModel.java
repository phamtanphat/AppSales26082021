package com.example.appsales26082021.model;

import java.util.List;

public class CartModel {
    public int total;
    public List<FoodModel> items;

    @Override
    public String toString() {
        return "CartModel{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}

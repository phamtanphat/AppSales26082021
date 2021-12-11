package com.example.appsales26082021.model;

import java.io.Serializable;
import java.util.List;

public class CartModel implements Serializable {
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

package com.example.appsales26082021.model;

import java.io.Serializable;
import java.util.List;

public class FoodModel implements Serializable {
    public String foodId;
    public String foodName;
    public List<ImageModel> images;
    public String description;
    public int quantity;
    public int price;
    public String cateId;
    public String cateName;
    public String createdAt;
    public String updatedAt;


    @Override
    public String toString() {
        return "FoodModel{" +
                "foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", images=" + images +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", cateId='" + cateId + '\'' +
                ", cateName='" + cateName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
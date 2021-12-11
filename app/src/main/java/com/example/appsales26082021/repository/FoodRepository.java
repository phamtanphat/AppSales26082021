package com.example.appsales26082021.repository;

import com.example.appsales26082021.api.ApiRequest;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.model.FoodModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class FoodRepository {
    private ApiRequest apiRequest;

    @Inject
    public FoodRepository(ApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    public Call<ResourceType<List<FoodModel>>> fetchListFoods() {
        return apiRequest.fetchListFoods();
    }

    public Call<ResourceType<CartModel>> fetchCart() {
        return apiRequest.fetchCart();
    }
}

package com.example.appsales26082021.repository;

import com.example.appsales26082021.api.ApiRequest;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.model.OrderModel;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;

public class OrderRepository {
    private ApiRequest apiRequest;

    @Inject
    public OrderRepository(ApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    public Call<ResourceType<OrderModel>> fetchTotalCountCart(){
        return apiRequest.fetchTotalCountCart();
    }

    public Call<ResourceType<OrderModel>> addCart(String foodId){
        HashMap<String ,String> map = new HashMap<>();
        map.put("foodId",foodId);
        return apiRequest.addCart(map);
    }

    public Call<ResourceType<String>> updateCart(String orderId, String foodId , int quantity){
        HashMap<String ,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        map.put("foodId",foodId);
        map.put("quantity",quantity);
        return apiRequest.updateCart(map);
    }
}

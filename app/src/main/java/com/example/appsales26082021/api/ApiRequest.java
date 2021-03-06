package com.example.appsales26082021.api;

import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.model.OrderModel;
import com.example.appsales26082021.model.UserModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface ApiRequest {
    @POST("api/v1/user/sign-in")
    Call<ResourceType<UserModel>> signIn(@Body UserModel userModel);

    @POST("api/v1/user/sign-up")
    Call<ResourceType<UserModel>> signUp(@Body UserModel userModel);

    @GET("api/v1/food/list/0/10")
    Call<ResourceType<List<FoodModel>>> fetchListFoods();

    @GET("api/v1/order/count/shopping-cart")
    Call<ResourceType<OrderModel>> fetchTotalCountCart();

    @GET("api/v1/order/shopping-cart")
    Call<ResourceType<CartModel>> fetchCart();

    @POST("api/v1/order/add-to-cart")
    Call<ResourceType<OrderModel>> addCart(@Body HashMap<String,String> data);

    @POST("api/v1/order/update")
    Call<ResourceType<String>> updateCart(@Body HashMap<String,Object> data);

    @HTTP(method = "DELETE", path = "api/v1/order/delete", hasBody = true)
    Call<ResourceType<CartModel>> deleteItemCart(@Body HashMap<String,String> data);

    @POST("api/v1/order/confirm")
    Call<ResourceType<String>> confirm(@Body HashMap<String,String> data);
}

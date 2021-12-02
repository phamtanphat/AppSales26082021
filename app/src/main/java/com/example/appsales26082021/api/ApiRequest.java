package com.example.appsales26082021.api;

import com.example.appsales26082021.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequest {
    @POST("api/v1/user/sign-in")
    Call<ResourceType<UserModel>> signIn(@Body UserModel userModel);
}

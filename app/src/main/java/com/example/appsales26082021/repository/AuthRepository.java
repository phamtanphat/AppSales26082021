package com.example.appsales26082021.repository;

import com.example.appsales26082021.api.ApiRequest;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.model.UserModel;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthRepository {
    private ApiRequest apiRequest;

    @Inject
    public AuthRepository(ApiRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    public Call<ResourceType<UserModel>> signIn(UserModel userModel){
       return apiRequest.signIn(userModel);
    }

    public Call<ResourceType<UserModel>> signUp(UserModel userModel){
        return apiRequest.signUp(userModel);
    }
}

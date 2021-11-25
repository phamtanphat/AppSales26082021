package com.example.appsales26082021;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestApi {

    @GET("KhoaPhamTraining/json/tien/demo1.json")
    Call<Demo1> callDemo1();
}

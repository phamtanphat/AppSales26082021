package com.example.appsales26082021;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    OkHttpClient provideClient(){
        return new OkHttpClient.Builder()
                .readTimeout(30 , TimeUnit.SECONDS)
                .writeTimeout(30 , TimeUnit.SECONDS)
                .connectTimeout(30 , TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
    }

    @Singleton
    @Provides
    Gson provideGson(){
        return new GsonBuilder().setLenient().create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson , OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://khoapham.vn/")
                .build();
    }

    @Singleton
    @Provides
    RequestApi provideRequestApi(Retrofit retrofit){
        return retrofit.create(RequestApi.class);
    }

}

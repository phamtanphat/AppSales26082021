package com.example.appsales26082021.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.model.FoodModel;
import com.example.appsales26082021.model.UserModel;
import com.example.appsales26082021.repository.FoodRepository;
import com.example.appsales26082021.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<ResourceType<List<FoodModel>>> foodData = new MutableLiveData<>();

    @Inject
    public MainViewModel(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public LiveData<ResourceType<List<FoodModel>>> getListFoods(){
        return foodData;
    }

    public void fetchListFoods(){
        foodRepository.fetchListFoods().enqueue(new Callback<ResourceType<List<FoodModel>>>() {
            @Override
            public void onResponse(Call<ResourceType<List<FoodModel>>> call, Response<ResourceType<List<FoodModel>>> response) {
                if (response.errorBody() != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        foodData.setValue(new ResourceType.Error<>(message));
                        return;
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                foodData.setValue(new ResourceType.Success<>(response.body().data));
            }

            @Override
            public void onFailure(Call<ResourceType<List<FoodModel>>> call, Throwable t) {
                foodData.setValue(new ResourceType.Error<>(t.getMessage()));
            }
        });
    }

}

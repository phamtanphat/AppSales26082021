package com.example.appsales26082021.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.api.ApiRequest;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.repository.OrderRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {
    private OrderRepository repository;
    private MutableLiveData<ResourceType<String>> dataUpdate = new MutableLiveData<>();

    @Inject
    public CartViewModel(OrderRepository orderRepository) {
        repository = orderRepository;
    }

    public LiveData<ResourceType<String>> getDataUpdate() {
        return dataUpdate;
    }

    public void updateCart(String orderId, String foodId , int quantity){
        repository.updateCart(orderId, foodId, quantity)
                .enqueue(new Callback<ResourceType<String>>() {
                    @Override
                    public void onResponse(Call<ResourceType<String>> call, Response<ResourceType<String>> response) {
                        if (response.errorBody() != null){
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                String message = jsonObject.getString("message");
                                dataUpdate.setValue(new ResourceType.Error<>(message));
                                return;
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        dataUpdate.setValue(new ResourceType.Success<>(response.body().data));
                    }

                    @Override
                    public void onFailure(Call<ResourceType<String>> call, Throwable t) {
                        dataUpdate.setValue(new ResourceType.Error<>(t.getMessage()));
                    }
                });
    }

}

package com.example.appsales26082021.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.model.CartModel;
import com.example.appsales26082021.repository.FoodRepository;
import com.example.appsales26082021.repository.OrderRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {
    private OrderRepository orderRepository;
    private FoodRepository foodRepository;

    private MutableLiveData<ResourceType<String>> dataUpdate = new MutableLiveData<>();
    private MutableLiveData<ResourceType<CartModel>> dataCart = new MutableLiveData<>();
    private MutableLiveData<ResourceType<String>> dataConfirm = new MutableLiveData<>();

    @Inject
    public CartViewModel(OrderRepository orderRepository, FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
    }

    public LiveData<ResourceType<String>> getDataUpdate() {
        return dataUpdate;
    }

    public LiveData<ResourceType<CartModel>> getDataCart() {
        return dataCart;
    }

    public LiveData<ResourceType<String>> getConfirm() {
        return dataConfirm;
    }

    public void updateCart(String orderId, String foodId, int quantity) {
        orderRepository.updateCart(orderId, foodId, quantity)
                .enqueue(new Callback<ResourceType<String>>() {
                    @Override
                    public void onResponse(Call<ResourceType<String>> call, Response<ResourceType<String>> response) {
                        if (response.errorBody() != null) {
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

    public void fetchCart(){
        foodRepository.fetchCart().enqueue(new Callback<ResourceType<CartModel>>() {
            @Override
            public void onResponse(Call<ResourceType<CartModel>> call, Response<ResourceType<CartModel>> response) {
                if (response.errorBody() != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        String code = jsonObject.getString("code");
                        dataCart.setValue(new ResourceType.Error<>(code + " : " + message));
                        return;
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (response.body().data == null){
                    dataCart.setValue(new ResourceType.Success<>(new CartModel()));
                }else{
                    dataCart.setValue(new ResourceType.Success<>(response.body().data));
                }
            }

            @Override
            public void onFailure(Call<ResourceType<CartModel>> call, Throwable t) {
                dataCart.setValue(new ResourceType.Error<>(t.getMessage()));
            }
        });
    }

    public void deleteItemCart(String foodId){
        orderRepository.deleteItemCart(foodId).enqueue(new Callback<ResourceType<CartModel>>() {
            @Override
            public void onResponse(Call<ResourceType<CartModel>> call, Response<ResourceType<CartModel>> response) {
                if (response.errorBody() != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        String code = jsonObject.getString("code");
                        dataCart.setValue(new ResourceType.Error<>(code + " : " + message));
                        return;
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (response.body().data == null){
                    dataCart.setValue(new ResourceType.Success<>(new CartModel()));
                }else{
                    dataCart.setValue(new ResourceType.Success<>(response.body().data));
                }
            }

            @Override
            public void onFailure(Call<ResourceType<CartModel>> call, Throwable t) {
                dataCart.setValue(new ResourceType.Error<>(t.getMessage()));
            }
        });
    }

    public void confirm(String orderId){
        orderRepository.confirm(orderId).enqueue(new Callback<ResourceType<String>>() {
            @Override
            public void onResponse(Call<ResourceType<String>> call, Response<ResourceType<String>> response) {
                if (response.errorBody() != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        String code = jsonObject.getString("code");
                        dataConfirm.setValue(new ResourceType.Error<>(code + " : " + message));
                        return;
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                dataConfirm.setValue(new ResourceType.Success<>(response.body().data));
            }

            @Override
            public void onFailure(Call<ResourceType<String>> call, Throwable t) {
                dataCart.setValue(new ResourceType.Error<>(t.getMessage()));
            }
        });
    }
}

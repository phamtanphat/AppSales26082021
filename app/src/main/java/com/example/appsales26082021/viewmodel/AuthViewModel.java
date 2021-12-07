package com.example.appsales26082021.viewmodel;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.model.UserModel;
import com.example.appsales26082021.repository.AuthRepository;
import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.util.SharePref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {
    public SharePref sharePref;
    private AuthRepository authRepository;
    private MutableLiveData<ResourceType<UserModel>> userData = new MutableLiveData<>();

    @Inject
    public AuthViewModel(AuthRepository authRepository, SharePref sharePref) {
        this.authRepository = authRepository;
        this.sharePref = sharePref;
    }

    public LiveData<ResourceType<UserModel>> getUserData() {
        return userData;
    }

    public void signIn(UserModel userModel) {
        userData.setValue(new ResourceType.Loading<>(null));
        Call<ResourceType<UserModel>> data = authRepository.signIn(userModel);
        data.enqueue(new Callback<ResourceType<UserModel>>() {
            @Override
            public void onResponse(Call<ResourceType<UserModel>> call, Response<ResourceType<UserModel>> response) {
                if (response.errorBody() != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        userData.setValue(new ResourceType.Error<>(message));
                        return;
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                userData.setValue(new ResourceType.Success<>(response.body().data));
                sharePref.setToken(Constant.KEY_TOKEN,response.body().data.getToken());
            }

            @Override
            public void onFailure(Call<ResourceType<UserModel>> call, Throwable t) {
                userData.setValue(new ResourceType.Error<>(t.getMessage()));
            }
        });
    }

    public void signUp(UserModel userModel) {
        userData.setValue(new ResourceType.Loading<>(null));
        Call<ResourceType<UserModel>> data = authRepository.signUp(userModel);
        data.enqueue(new Callback<ResourceType<UserModel>>() {
            @Override
            public void onResponse(Call<ResourceType<UserModel>> call, Response<ResourceType<UserModel>> response) {
                if (response.errorBody() != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        userData.setValue(new ResourceType.Error<>(message));
                        return;
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                userData.setValue(new ResourceType.Success<>(response.body().data));
            }

            @Override
            public void onFailure(Call<ResourceType<UserModel>> call, Throwable t) {
                userData.setValue(new ResourceType.Error<>(t.getMessage()));
            }
        });
    }

}

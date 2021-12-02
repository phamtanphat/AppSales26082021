package com.example.appsales26082021.view.auth;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.R;
import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.model.UserModel;
import com.example.appsales26082021.viewmodel.AuthViewModel;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignInActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelFactoryProvider provider;

    AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        authViewModel = new ViewModelProvider(this,provider).get(AuthViewModel.class);

        authViewModel.getUserData().observe(this, new Observer<ResourceType<UserModel>>() {
            @Override
            public void onChanged(ResourceType<UserModel> userModelResourceType) {
                if (userModelResourceType.status == ResourceType.Status.LOADING){
                    Log.d("BBB","Loading");
                }else if(userModelResourceType.status == ResourceType.Status.SUCCESS){
                    Log.d("BBB",userModelResourceType.data.toString());
                }else {
                    Log.d("BBB",userModelResourceType.message);
                }
            }
        });

        authViewModel.signIn(new UserModel("nguyenvana@gmail.com","12345678"));


    }
}
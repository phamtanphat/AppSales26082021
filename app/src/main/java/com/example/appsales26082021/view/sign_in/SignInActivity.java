package com.example.appsales26082021.view.sign_in;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.databinding.ActivitySignInBinding;
import com.example.appsales26082021.model.UserModel;
import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.view.main.MainActivity;
import com.example.appsales26082021.view.sign_up.SignUpActivity;
import com.example.appsales26082021.viewmodel.AuthViewModel;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignInActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelFactoryProvider provider;

    @Inject
    SharedPreferences sharedPreferences;

    AuthViewModel mAuthViewModel;

    private ActivitySignInBinding mBinding;
    private final int REQUEST_CODE_USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.toolbarSignIn.setTitle("Sign In");
        mBinding.toolbarSignIn.setTitleTextColor(Color.WHITE);

        mAuthViewModel = new ViewModelProvider(this, provider).get(AuthViewModel.class);

        observerData();
        event();
    }

    private void event() {
        mBinding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mBinding.textEditEmail.getText().toString();
                String password = mBinding.textEditPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "B???n ch??a nh???p ????? th??ng tin!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuthViewModel.signIn(new UserModel(email, password));
            }
        });

        mBinding.textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(SignInActivity.this, SignUpActivity.class),REQUEST_CODE_USER);
            }
        });
    }

    private void observerData() {
        mAuthViewModel.getUserData().observe(this, new Observer<ResourceType<UserModel>>() {
            @Override
            public void onChanged(ResourceType<UserModel> userModelResourceType) {
                if (userModelResourceType.status == ResourceType.Status.LOADING) {
                    mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
                } else if (userModelResourceType.status == ResourceType.Status.SUCCESS) {
                    Toast.makeText(SignInActivity.this, "????ng nh???p th??nh c??ng", Toast.LENGTH_SHORT).show();
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, userModelResourceType.message, Toast.LENGTH_SHORT).show();
                    mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_USER && resultCode == RESULT_OK && data != null){
            UserModel userModel = (UserModel) data.getSerializableExtra(Constant.KEY_USER_MODEL);
            mBinding.textEditEmail.setText(userModel.getEmail());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
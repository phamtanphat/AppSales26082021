package com.example.appsales26082021.view.sign_up;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appsales26082021.api.ResourceType;
import com.example.appsales26082021.databinding.ActivitySignUpBinding;
import com.example.appsales26082021.model.UserModel;
import com.example.appsales26082021.util.Constant;
import com.example.appsales26082021.view.sign_in.SignInActivity;
import com.example.appsales26082021.viewmodel.AuthViewModel;
import com.example.appsales26082021.viewmodel.ViewModelFactoryProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignUpActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelFactoryProvider provider;

    private AuthViewModel mAuthViewModel;
    private ActivitySignUpBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mAuthViewModel = new ViewModelProvider(this, provider).get(AuthViewModel.class);

        observerData();
        event();
    }

    private void event() {

        mBinding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = mBinding.textEditName.getText().toString();
                String email = mBinding.textEditEmail.getText().toString();
                String phone = mBinding.textEditPhone.getText().toString();
                String password = mBinding.textEditPassword.getText().toString();
                String address = mBinding.textEditAddress.getText().toString();

                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || address.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Bạn chưa truyền đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuthViewModel.signUp(new UserModel(fullName, email, password, phone, address));
            }
        });
    }

    private void observerData() {
        mAuthViewModel.getUserData().observe(this, new Observer<ResourceType<UserModel>>() {
            @Override
            public void onChanged(ResourceType<UserModel> userModelResourceType) {
                if (userModelResourceType != null) {
                    switch (userModelResourceType.status) {
                        case LOADING:
                            mBinding.containerLoading.layoutLoading.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            Toast.makeText(SignUpActivity.this, userModelResourceType.message, Toast.LENGTH_SHORT).show();
                            mBinding.containerLoading.layoutLoading.setVisibility(View.GONE);
                            break;
                        case SUCCESS:
                            Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            mBinding.containerLoading.layoutLoading.setVisibility(View.GONE);
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            intent.putExtra(Constant.KEY_USER_MODEL, userModelResourceType.data);
                            setResult(RESULT_OK, intent);
                            finish();
                            break;
                    }
                }
            }
        });
    }
}
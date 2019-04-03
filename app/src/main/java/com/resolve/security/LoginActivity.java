package com.resolve.security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.resolve.security.datados.LoginRequest;
import com.resolve.security.datados.LoginResponse;
import com.resolve.security.utils.DeviceIDs;
import com.resolve.security.utils.Preferences;
import com.resolve.security.web.WebAPI;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.resolve.security.utils.Preferences.USER_DATA;
import static com.resolve.security.utils.Preferences.USER_ID;
import static com.resolve.security.utils.Validators.validateEmail;
import static com.resolve.security.utils.Validators.validatePassword;

public class LoginActivity extends BaseActivity {

    private TextInputLayout emailInputLayout, passwordInputLayout;
    private Button btnLogin;

    private Disposable callOTP;

    @Inject WebAPI mWebAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((App) getApplication()).getNetComponent().inject(this);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin(){
        hideKeyboard();
        String username = emailInputLayout.getEditText().getText().toString();
        String password = passwordInputLayout.getEditText().getText().toString();
        if (!validateEmail(username)) {
            emailInputLayout.setError("Not a valid email address!");
        } else if (!validatePassword(password)) {
            passwordInputLayout.setError("Not a valid password!");
        } else {
            emailInputLayout.setErrorEnabled(false);
            passwordInputLayout.setErrorEnabled(false);
            login(username, password);
        }
    }

    private void goToDashboard(){
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }

    private void goToOTPVerification(){
        Intent i = new Intent(this, OTPVerificationPage.class);
        startActivity(i);
    }

    private void login(String userName, String password) {

        showProgress("Attempting Login...");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(userName);
        loginRequest.setPassword(password);
        loginRequest.setToken(DeviceIDs.id(this));

        Observable<LoginResponse> call = mWebAPI.login(loginRequest);

        callOTP = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> onSuccess(loginResponse),
                        throwable -> onError(throwable),
                        () -> hideProgress());
    }

    private void onError(Throwable throwable) {
        Log.i("Error", throwable.getMessage());
    }

    private void onSuccess(LoginResponse loginResponse) {
        Log.i("Response", loginResponse.toString());

        if(loginResponse != null && loginResponse.getStatus()){
            Preferences.saveValue(USER_ID, loginResponse.getUserId());
            if("true".equalsIgnoreCase(loginResponse.getCheckOtp())){
                // Go To OTP Screen
                goToOTPVerification();
            } else {
                // Go To Dashboard Screen.
                Preferences.saveValue(USER_DATA, new Gson().toJson(loginResponse.getOutput()));
                goToDashboard();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (callOTP != null)
            callOTP.dispose();
    }
}

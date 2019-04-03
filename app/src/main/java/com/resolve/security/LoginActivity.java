package com.resolve.security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import static com.resolve.security.utils.Validators.validateEmail;
import static com.resolve.security.utils.Validators.validatePassword;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailInputLayout, passwordInputLayout;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    doLogin();
                }
            }
        });
    }

    private void doLogin(){
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}

package com.resolve.security;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.resolve.security.datados.LoginResponse;
import com.resolve.security.datados.Output;
import com.resolve.security.utils.DialogUtils;
import com.resolve.security.utils.Preferences;

import static com.resolve.security.utils.Preferences.USER_DATA;

public class SplashActivity extends AppCompatActivity {

    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        boolean isCameraGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean isLocationGranted =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean isWriteExternalGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;


        if (isCameraGranted && isLocationGranted && isWriteExternalGranted) {
            navigateUser();
        } else {
            requestPermissions();
        }
    }

    private void navigateUser() {
        String userData = Preferences.getString(USER_DATA);
        if (TextUtils.isEmpty(userData)) {
            goTo(0);
        } else {
            goTo(1);
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                102);
    }

    private void goTo(int mode) {
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(this, mode == 0 ? LoginActivity.class : Main2Activity.class);
            startActivity(mainIntent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 102) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                navigateUser();
            } else {
                DialogUtils.showDialog(this,
                        getString(R.string.permissions_hint_message),
                        getString(R.string.permissions_denied_title), new DialogUtils.DialogEvents() {
                            @Override
                            public void onSuccess() {
                                requestPermissions();
                            }

                            @Override
                            public void onFailure() {
                                finish();
                            }
                        });
            }
        }
    }

}

package com.resolve.security;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.resolve.security.datados.CheckOTPRequest;
import com.resolve.security.datados.CheckOTPResponse;
import com.resolve.security.utils.DeviceIDs;
import com.resolve.security.utils.Preferences;
import com.resolve.security.web.WebAPI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.resolve.security.utils.Preferences.USER_DATA;

public class OTPVerificationPage extends BaseActivity {

    @Inject
    WebAPI mWebAPI;

    @BindView(R.id.etOTP)
    EditText etOTP;
    @BindView(R.id.btnOtp)
    Button btnOtp;

    private Disposable callOTP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otrp_verification);
        ((App) getApplication()).getNetComponent().inject(this);
        ButterKnife.bind(this);
//        etOTP.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        btnOtp.setOnClickListener(v -> {
            String otp = etOTP.getText().toString();
            if (otp == null || otp.length() != 6) {
                showToast(getString(R.string.otp_input_error));
            } else {
                checkOTPService(otp);
            }
        });
    }

    private void checkOTPService(String otp) {
        showProgress("Validating OTP...");

        CheckOTPRequest checkOTPRequest = new CheckOTPRequest();
        checkOTPRequest.setDeviceId(DeviceIDs.id());
        checkOTPRequest.setOtp(otp);
        checkOTPRequest.setUserId(Preferences.getString(Preferences.USER_ID));

        Observable<CheckOTPResponse> call = mWebAPI.checkOTP(checkOTPRequest);

        callOTP = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccess,
                        this::onError,
                        this::hideProgress
                );
    }

    private void onError(Throwable throwable) {
        showToast(throwable.getMessage());
    }

    private void onSuccess(CheckOTPResponse checkOTPResponse) {
        if (checkOTPResponse != null && checkOTPResponse.getStatus()) {
            Preferences.saveValue(USER_DATA, new Gson().toJson(checkOTPResponse.getOutput()));
            goToDashboard();
        } else {
            showToast(checkOTPResponse.getMessage());
        }
    }

    private void goToDashboard() {
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (callOTP != null)
            callOTP.dispose();
    }
}

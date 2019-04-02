
package com.resolve.security.datados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOTPRequest {

    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("device_id")
    @Expose
    private String deviceId;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}

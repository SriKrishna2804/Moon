
package com.resolve.security.datados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("check_otp")
    @Expose
    private String checkOtp;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("output")
    @Expose
    private Output output;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCheckOtp() {
        return checkOtp;
    }

    public void setCheckOtp(String checkOtp) {
        this.checkOtp = checkOtp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", checkOtp='" + checkOtp + '\'' +
                ", userId='" + userId + '\'' +
                ", output=" + output +
                '}';
    }
}

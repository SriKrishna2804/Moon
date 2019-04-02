
package com.resolve.security.datados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageVerificationResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("output")
    @Expose
    private Integer output;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

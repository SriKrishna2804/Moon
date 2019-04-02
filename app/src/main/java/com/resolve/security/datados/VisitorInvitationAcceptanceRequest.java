
package com.resolve;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitorInvitationAcceptanceRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("request_id")
    @Expose
    private String requestId;
    @SerializedName("accepted")
    @Expose
    private String accepted;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

}

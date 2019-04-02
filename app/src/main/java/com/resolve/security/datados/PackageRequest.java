
package com.resolve.security.datados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("package_from")
    @Expose
    private String packageFrom;
    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("package_to")
    @Expose
    private String packageTo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;

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

    public String getPackageFrom() {
        return packageFrom;
    }

    public void setPackageFrom(String packageFrom) {
        this.packageFrom = packageFrom;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getPackageTo() {
        return packageTo;
    }

    public void setPackageTo(String packageTo) {
        this.packageTo = packageTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}

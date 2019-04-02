
package com.resolve.security.datados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("package_from")
    @Expose
    private String packageFrom;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("package_to")
    @Expose
    private String packageTo;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("datetime")
    @Expose
    private String datetime;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageFrom() {
        return packageFrom;
    }

    public void setPackageFrom(String packageFrom) {
        this.packageFrom = packageFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageTo() {
        return packageTo;
    }

    public void setPackageTo(String packageTo) {
        this.packageTo = packageTo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}

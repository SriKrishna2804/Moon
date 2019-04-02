
package com.resolve.security.datados;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecurityScanResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<SecurityScanDatum> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SecurityScanDatum> getData() {
        return data;
    }

    public void setData(List<SecurityScanDatum> data) {
        this.data = data;
    }

}

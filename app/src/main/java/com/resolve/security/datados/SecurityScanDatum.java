
package com.resolve.security.datados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecurityScanDatum {

    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("display_image")
    @Expose
    private String displayImage;
    @SerializedName("display_flat")
    @Expose
    private String displayFlat;
    @SerializedName("hidden_qr_code")
    @Expose
    private String hiddenQrCode;
    @SerializedName("display_entry")
    @Expose
    private String displayEntry;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    public String getDisplayFlat() {
        return displayFlat;
    }

    public void setDisplayFlat(String displayFlat) {
        this.displayFlat = displayFlat;
    }

    public String getHiddenQrCode() {
        return hiddenQrCode;
    }

    public void setHiddenQrCode(String hiddenQrCode) {
        this.hiddenQrCode = hiddenQrCode;
    }

    public String getDisplayEntry() {
        return displayEntry;
    }

    public void setDisplayEntry(String displayEntry) {
        this.displayEntry = displayEntry;
    }

}


package com.resolve;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitorRelationDatum {

    @SerializedName("visitor_relation_id")
    @Expose
    private String visitorRelationId;
    @SerializedName("visitor_relation_name")
    @Expose
    private String visitorRelationName;

    public String getVisitorRelationId() {
        return visitorRelationId;
    }

    public void setVisitorRelationId(String visitorRelationId) {
        this.visitorRelationId = visitorRelationId;
    }

    public String getVisitorRelationName() {
        return visitorRelationName;
    }

    public void setVisitorRelationName(String visitorRelationName) {
        this.visitorRelationName = visitorRelationName;
    }

}

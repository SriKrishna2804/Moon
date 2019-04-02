
package com.resolve;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitorInviteRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("visitor_relation")
    @Expose
    private String visitorRelation;
    @SerializedName("visitor_mobile")
    @Expose
    private String visitorMobile;
    @SerializedName("visitor_name")
    @Expose
    private String visitorName;
    @SerializedName("visitors_count")
    @Expose
    private String visitorsCount;
    @SerializedName("visitor_location_id")
    @Expose
    private String visitorLocationId;
    @SerializedName("visit_duration_from")
    @Expose
    private String visitDurationFrom;
    @SerializedName("visit_duration_to")
    @Expose
    private String visitDurationTo;

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

    public String getVisitorRelation() {
        return visitorRelation;
    }

    public void setVisitorRelation(String visitorRelation) {
        this.visitorRelation = visitorRelation;
    }

    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorsCount() {
        return visitorsCount;
    }

    public void setVisitorsCount(String visitorsCount) {
        this.visitorsCount = visitorsCount;
    }

    public String getVisitorLocationId() {
        return visitorLocationId;
    }

    public void setVisitorLocationId(String visitorLocationId) {
        this.visitorLocationId = visitorLocationId;
    }

    public String getVisitDurationFrom() {
        return visitDurationFrom;
    }

    public void setVisitDurationFrom(String visitDurationFrom) {
        this.visitDurationFrom = visitDurationFrom;
    }

    public String getVisitDurationTo() {
        return visitDurationTo;
    }

    public void setVisitDurationTo(String visitDurationTo) {
        this.visitDurationTo = visitDurationTo;
    }

}

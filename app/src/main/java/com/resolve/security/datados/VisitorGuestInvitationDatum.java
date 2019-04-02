
package com.resolve.security.datados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitorGuestInvitationDatum {

    @SerializedName("request_id")
    @Expose
    private String requestId;
    @SerializedName("visitor_name")
    @Expose
    private String visitorName;
    @SerializedName("visitor_relation")
    @Expose
    private String visitorRelation;
    @SerializedName("flat_name")
    @Expose
    private String flatName;
    @SerializedName("visitor_mobile")
    @Expose
    private String visitorMobile;
    @SerializedName("visit_approval_type")
    @Expose
    private String visitApprovalType;
    @SerializedName("visit_duration_from")
    @Expose
    private String visitDurationFrom;
    @SerializedName("visit_duration_to")
    @Expose
    private String visitDurationTo;
    @SerializedName("visit_status")
    @Expose
    private String visitStatus;
    @SerializedName("visitors_count")
    @Expose
    private String visitorsCount;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorRelation() {
        return visitorRelation;
    }

    public void setVisitorRelation(String visitorRelation) {
        this.visitorRelation = visitorRelation;
    }

    public String getFlatName() {
        return flatName;
    }

    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile;
    }

    public String getVisitApprovalType() {
        return visitApprovalType;
    }

    public void setVisitApprovalType(String visitApprovalType) {
        this.visitApprovalType = visitApprovalType;
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

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }

    public String getVisitorsCount() {
        return visitorsCount;
    }

    public void setVisitorsCount(String visitorsCount) {
        this.visitorsCount = visitorsCount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}

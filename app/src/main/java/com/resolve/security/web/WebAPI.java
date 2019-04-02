package com.resolve.security.web;

import com.resolve.VisitorGuestRequestResponse;
import com.resolve.VisitorInvitationAcceptanceRequest;
import com.resolve.VisitorInvitationAcceptanceResponse;
import com.resolve.VisitorInvitationCancelRequest;
import com.resolve.VisitorInvitationCancelResponse;
import com.resolve.VisitorInviteListRequest;
import com.resolve.VisitorInviteRequest;
import com.resolve.VisitorInviteResponse;
import com.resolve.VisitorRelationRequest;
import com.resolve.VisitorRelationResponse;
import com.resolve.security.datados.ChangePasswordRequest;
import com.resolve.security.datados.ChangePasswordResponse;
import com.resolve.security.datados.CheckOTPRequest;
import com.resolve.security.datados.CheckOTPResponse;
import com.resolve.security.datados.EntryBookRequest;
import com.resolve.security.datados.EntryBookResponse;
import com.resolve.security.datados.EntryListRequest;
import com.resolve.security.datados.EntryListResponse;
import com.resolve.security.datados.LoginRequest;
import com.resolve.security.datados.LoginResponse;
import com.resolve.security.datados.PackageListRequest;
import com.resolve.security.datados.PackageListResponse;
import com.resolve.security.datados.PackageRResponse;
import com.resolve.security.datados.PackageRequest;
import com.resolve.security.datados.PackageVerificationResponse;
import com.resolve.security.datados.PackageVerificationResquest;
import com.resolve.security.datados.RequestVerifyResponse;
import com.resolve.security.datados.SecurityScanRequest;
import com.resolve.security.datados.SecurityScanResponse;
import com.resolve.security.datados.VisitorExitRequest;
import com.resolve.security.datados.VisitorExitResponse;
import com.resolve.security.datados.VisitorGuestInvitationRequest;
import com.resolve.security.datados.VisitorGuestInvitationResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebAPI {

    @POST("/api/login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/login/changepassword")
    Observable<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @POST("/api/login/checkotp")
    Observable<CheckOTPResponse> checkOTP(@Body CheckOTPRequest checkOTPRequest);

    @POST("/api/entry/scan")
    Observable<SecurityScanResponse> securityScan(@Body SecurityScanRequest securityScanRequest);

    @POST("/api/entry/entry_book")
    Observable<EntryBookResponse> addEntryBook(@Body EntryBookRequest entryBookRequest);

    @POST("/api/entry/list")
    Observable<EntryListResponse> entryList(@Body EntryListRequest entryListRequest);

    @POST("/api/packages/request")
    Observable<PackageRResponse> packageRequest(@Body PackageRequest entryListRequest);

    @POST("/api/packages/verify_package")
    Observable<PackageVerificationResponse> packageVerification(@Body PackageVerificationResquest packageVerificationResquest);

    @POST("/api/packages/list")
    Observable<PackageListResponse> packageList(@Body PackageListRequest packageListRequest);

    @POST("/api/visitors/visitor_relations")
    Observable<VisitorRelationResponse> vistorRelations(@Body VisitorRelationRequest packageListRequest);

    @POST("/api/visitors/request")
    Observable<VisitorInviteResponse> visitorRequest(@Body VisitorInviteRequest packageListRequest);

    // Image File in "Image" Field
    @FormUrlEncoded
    @POST("/api/visitors/request_verify")
    Observable<RequestVerifyResponse> visitorRequestVerify(@Field("token") String token,
                                                          @Field("user_id") String user_id,
                                                          @Field("request_code") String request_code,
                                                          @Field("request_id") String request_id,
                                                          @Field("image") String image);

    @POST("/api/visitors/invitations")
    Observable<VisitorInviteListRequest> visitorInvitations(@Body VisitorInviteListRequest packageListRequest);

    @POST("/api/visitors/entry_list")
    Observable<RequestBody> visitorEntryList(@Body PackageListRequest packageListRequest);

    @POST("/api/visitors/exit")
    Observable<VisitorExitResponse> visitorInvitations(@Body VisitorExitRequest visitorExitRequest);

    @POST("/api/visitors/cancel_invitation")
    Observable<VisitorInvitationCancelResponse> visitorCancelInvitation(@Body VisitorInvitationCancelRequest visitorInvitationCancelRequest);

    @POST("/api/visitors/guest_request")
    Observable<VisitorGuestRequestResponse> visitorGuestRequest(@Body VisitorGuestInvitationRequest visitorGuestInvitationRequest);

    @POST("/api/visitors/guest_invitations")
    Observable<VisitorGuestInvitationResponse> visitorInvitations(@Body VisitorGuestInvitationRequest visitorGuestInvitationRequest);

    @POST("/api/visitors/invitation_acceptence")
    Observable<VisitorInvitationAcceptanceResponse> visitorInvitations(@Body VisitorInvitationAcceptanceRequest visitorInvitationAcceptanceRequest);

}

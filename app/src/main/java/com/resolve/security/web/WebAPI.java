package com.resolve.security.web;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebAPI {

    @Headers({ "Content-Type:application/xml" })
    @POST("/api/login")
    Observable<String> login(@Body RequestBody loginRequest);

}

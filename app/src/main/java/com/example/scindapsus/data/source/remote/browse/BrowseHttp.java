package com.example.scindapsus.data.source.remote.browse;

import com.example.scindapsus.model.Auth;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ej on 4/25/2017.
 */

public interface BrowseHttp {
    @Headers({"Content-Type: application/json"})
    @POST("actions/login")
    Observable<Response<Void>> loadPlaysInfo(@Body Auth auth);
}

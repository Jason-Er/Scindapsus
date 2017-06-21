package com.example.scindapsus.data.source.remote.login;

import com.example.scindapsus.model.User;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ej on 2/28/2017.
 */

public interface LoginHttp {

    @Headers({"Content-Type: application/json"})
    @POST("login")
    Observable<Response<User>> login(@Body User user);

}

package com.example.scindapsus.data.source.remote.login;

import com.example.scindapsus.model.Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ej on 2/28/2017.
 */

public interface LoginHttp {
    @FormUrlEncoded
    @POST("actions/login")
    Observable<Token> login(@Field("name") String name, @Field("password") String password);
}

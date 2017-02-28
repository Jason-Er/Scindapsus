package com.example.scindapsus.data.source.remote;

import com.example.scindapsus.model.HttpResult;
import com.example.scindapsus.model.Token;

import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ej on 2/28/2017.
 */

public interface HttpActions {
    @POST("actions/login")
    Observable<HttpResult<Token>> login(@Field("name") String name, @Field("password") String password);
}

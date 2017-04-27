package com.example.scindapsus.data.source.remote.login;

import com.example.scindapsus.model.Auth;
import com.example.scindapsus.model.Token;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ej on 2/28/2017.
 */

public interface LoginHttp {

    @Headers({"Content-Type: application/json"})
    @POST("/login")
    Observable<Response<Void>> login(@Body Auth auth);

    /*

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("server?")
    Observable<GetLoginJson> Login(@Body RequestBody body);

    @GET("driver/v1/driver")
    Call<Driver> getAuthorizedDriver(@Header("authorization") String token,
                                 @Header("driver_id") Integer id);

    @POST("/api/v1/user/controller")
    void registerController(
            @Body MyBundleObject registrationBundle,
            @Header("x-company-device-token") String companyDeviceToken,
            @Header("x-company-device-guid") String companyDeviceGuid,
            Callback<ResponseObject> cb);
            */
}

package com.example.scindapsus.data.source.remote.image;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by ej on 4/28/2017.
 */

public interface ImageHttp {
    @GET
    Flowable<ResponseBody> getImage(@Header("Authorization") String token, @Url String url);
}

package com.example.scindapsus.data.source.remote.image;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by ej on 4/28/2017.
 */

public interface ImageHttp {
    @GET
    Observable<ResponseBody> getImage(@Header("Authorization") String token, @Url String url);
}

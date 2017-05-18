package com.example.scindapsus.data.source.remote.file;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ej on 5/5/2017.
 */

public interface FileHttp {
    @Streaming
    @GET
    Flowable<ResponseBody> getFile(@Header("Authorization") String token, @Url String url);
}

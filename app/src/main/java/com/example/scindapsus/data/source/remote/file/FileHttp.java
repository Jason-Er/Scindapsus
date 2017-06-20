package com.example.scindapsus.data.source.remote.file;

import com.example.scindapsus.model.UploadVoiceUrl;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ej on 5/5/2017.
 */

public interface FileHttp {
    @Streaming
    @GET
    Observable<Response<ResponseBody>> downloadFile(@Header("Authorization") String token, @Url String url);

    @Multipart
    @POST
    Observable<String> uploadFile(
            @Header("Authorization") String token,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file,
            @Url String url
    );

    @Multipart
    @POST("v1/file/{play}/{scene}/{line}/audio")
    Observable<UploadVoiceUrl> uploadOneAudio(
            @Header("Authorization") String token,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file,
            @Path("play") String play,
            @Path("scene") String scene,
            @Path("line") String line
    );
}

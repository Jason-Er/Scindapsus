package com.example.scindapsus.data.source.remote.file;

import com.example.scindapsus.model.UploadAudioUrl;

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
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ej on 5/5/2017.
 */

public interface FileHttp {
    @Streaming
    @GET
    Observable<Response<ResponseBody>> getFile(@Header("Authorization") String token, @Url String url);

    @Multipart
    @POST("upload")
    Observable<UploadAudioUrl> uploadFile(
            @Header("Authorization") String token,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

}

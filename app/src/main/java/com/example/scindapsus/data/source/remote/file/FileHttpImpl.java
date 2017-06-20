package com.example.scindapsus.data.source.remote.file;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.UploadVoiceUrl;
import com.example.scindapsus.model.adapter.CustomAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by ej on 5/5/2017.
 */

public class FileHttpImpl {
    final static String TAG = FileHttpImpl.class.getName();
    @Inject
    RetrofitUtil retrofitUtil;

    private FileHttp fileHttp;

    public FileHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CustomAdapterFactory.create()).create();
        fileHttp = retrofitUtil.createApi(FileHttp.class, gson);
    }

    public Observable<Response<ResponseBody>> downloadFile(String token, String url) {
        return fileHttp.downloadFile(token, url);
    }

    public Observable<String> uploadFile(String token, RequestBody description, MultipartBody.Part body, String url) {
        return fileHttp.uploadFile(token, description, body, url);
    }

    public Observable<UploadVoiceUrl> uploadOneAudio(String token, RequestBody description, MultipartBody.Part body, String play, String scene, String line) {
        return fileHttp.uploadOneAudio(token, description, body, play, scene, line);
    }

}

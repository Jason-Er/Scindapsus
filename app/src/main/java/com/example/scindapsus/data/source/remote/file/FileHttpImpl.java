package com.example.scindapsus.data.source.remote.file;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.UploadAudioUrl;

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
        fileHttp = retrofitUtil.createApi(FileHttp.class);
    }

    public Observable<Response<ResponseBody>> downloadFile(String token, String url) {
        return fileHttp.downloadFile(token, url);
    }

    public Observable<UploadAudioUrl> uploadFile(String token, RequestBody description, MultipartBody.Part body, String url) {
        return fileHttp.uploadFile(token, description, body, url);
    }

    public Observable<UploadAudioUrl> uploadOneAudio(String token, RequestBody description, MultipartBody.Part body, String play, String scene, String line) {
        return fileHttp.uploadOneAudio(token, description, body, play, scene, line);
    }

}

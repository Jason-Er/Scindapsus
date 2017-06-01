package com.example.scindapsus.data.source.remote.file;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.UploadAudioUrl;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    public void getFile(String token, Observer<Response<ResponseBody>> observer, String url) {
        fileHttp.getFile(token, url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public Observable<UploadAudioUrl> uploadFile(String token, RequestBody description, MultipartBody.Part body) {
        return fileHttp.uploadFile(token, description, body);
    }


}

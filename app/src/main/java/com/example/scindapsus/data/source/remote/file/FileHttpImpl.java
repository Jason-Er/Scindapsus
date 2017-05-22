package com.example.scindapsus.data.source.remote.file;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;

import java.io.InputStream;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


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

    public void getFile(String token, Observer<ResponseBody> observer, String url) {
        fileHttp.getFile(token, url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public InputStream getFile(String token, String url) {
        fileHttp.getFile(token, url)
                .map(new Function<ResponseBody, InputStream>() {
                    @Override
                    public InputStream apply(@NonNull ResponseBody responseBody) throws Exception {
                        InputStream is = responseBody.byteStream();
                        return is;
                    }
                })
                .subscribe(new Consumer<InputStream>() {
                    @Override
                    public void accept(@NonNull InputStream inputStream) throws Exception {
                        return;
                    }
                });
        return null;
    }

}

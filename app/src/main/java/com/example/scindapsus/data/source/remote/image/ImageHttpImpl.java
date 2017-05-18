package com.example.scindapsus.data.source.remote.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;

import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by ej on 4/28/2017.
 */

public class ImageHttpImpl {
    final static String TAG = ImageHttpImpl.class.getName();
    @Inject
    RetrofitUtil retrofitUtil;

    private ImageHttp imageHttp;

    public ImageHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        imageHttp = retrofitUtil.createApi(ImageHttp.class);
    }

    public void getImage(String token, Subscriber<Bitmap> observer, String url) {
        imageHttp.getImage(token, url)
                .map(new Function<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull ResponseBody responseBody) {
                        try(InputStream is = responseBody.byteStream()) {
                            return BitmapFactory.decodeStream(is);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

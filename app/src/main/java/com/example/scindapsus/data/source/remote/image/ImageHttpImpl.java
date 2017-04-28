package com.example.scindapsus.data.source.remote.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    public void getImage(String token, Subscriber<Bitmap> subscriber, String url) {
        imageHttp.getImage(token, url)
                .map(new Func1<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap call(ResponseBody responseBody) {
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
                .subscribe(subscriber);
    }

}

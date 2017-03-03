package com.example.scindapsus.data.source.remote;

import android.util.Log;

import com.example.scindapsus.data.source.remote.restful.Actions;
import com.example.scindapsus.model.HttpResult;
import com.example.scindapsus.model.Token;
import com.example.scindapsus.model.adapter.HttpResultAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ej on 2/28/2017.
 */

public class HttpMethods {
    final static String TAG = "HttpMethods";
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private Actions actions;

    public HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(HttpResultAdapterFactory.create()).create();

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        actions = retrofit.create(Actions.class);
    }

    public void login(Subscriber<Token> subscriber, String name, String password){
        Log.i(TAG,"Invoke method login");
        /*
        actions.login(name, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
                */
    }
}

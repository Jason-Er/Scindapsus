package com.example.scindapsus.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ej on 3/13/2017.
 */

public class RetrofitUtil {
    static Properties properties;
    public RetrofitUtil(@NonNull Properties properties) {
        RetrofitUtil.properties = properties;
    }

    public static <T> T createApi(@NonNull Class<T> tClass) {
        String BASE_URL = properties.getProperty("BASE_URL");
        int DEFAULT_TIMEOUT = Integer.parseInt(properties.getProperty("DEFAULT_TIMEOUT"));
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(tClass);
    }
    public static <T> T createApi(@NonNull Class<T> tClass, Gson gson) {
        String BASE_URL = properties.getProperty("BASE_URL");
        int DEFAULT_TIMEOUT = Integer.parseInt(properties.getProperty("DEFAULT_TIMEOUT"));
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(tClass);
    }
}

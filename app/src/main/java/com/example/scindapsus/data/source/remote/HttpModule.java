package com.example.scindapsus.data.source.remote;

import com.example.scindapsus.util.DataSourceFuncModel;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ej on 3/6/2017.
 */
@Module
public class HttpModule {
    @DataSourceFuncModel
    @Provides
    public Retrofit provideRetrofit(){ //Properties properties
        //String BASE_URL = properties.getProperty("BASE_URL");
        //int DEFAULT_TIMEOUT = Integer.parseInt(properties.getProperty("DEFAULT_TIMEOUT"));
        String BASE_URL = "http://192.1.112.31:8448/";
        int DEFAULT_TIMEOUT =5;
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
}

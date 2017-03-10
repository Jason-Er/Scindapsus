package com.example.scindapsus.data.source.remote;

import com.example.scindapsus.util.DataSourceFuncModelScope;

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
    @DataSourceFuncModelScope
    @Provides
    public Retrofit provideRetrofit(Properties properties){
        String BASE_URL = properties.getProperty("BASE_URL");
        int DEFAULT_TIMEOUT = Integer.parseInt(properties.getProperty("DEFAULT_TIMEOUT"));
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

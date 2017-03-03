package com.example.scindapsus.data.source;

import com.example.scindapsus.data.source.remote.HttpMethods;
import com.example.scindapsus.service.login.LoginServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/3/2017.
 */
@Module
public class DataSourceModule {
    @Singleton
    @Provides
    public HttpMethods provideHttpMethods(){
        return new HttpMethods();
    }
}

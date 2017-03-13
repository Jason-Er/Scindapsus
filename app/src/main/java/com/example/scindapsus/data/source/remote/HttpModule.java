package com.example.scindapsus.data.source.remote;

import com.example.scindapsus.util.DataSourceFuncModelScope;

import java.util.Properties;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/6/2017.
 */
@Module
public class HttpModule {
    @DataSourceFuncModelScope
    @Provides
    public RetrofitUtil provideRetrofitUtil(Properties properties) {
        return new RetrofitUtil(properties);
    }
}

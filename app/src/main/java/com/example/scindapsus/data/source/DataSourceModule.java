package com.example.scindapsus.data.source;

import com.example.scindapsus.data.source.remote.login.LoginHttpImpl;

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
    public LoginHttpImpl provideHttpMethods(){
        return new LoginHttpImpl();
    }
}

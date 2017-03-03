package com.example.scindapsus.service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/3/2017.
 */
@Module
public class LoginServiceModule {
    @Singleton
    @Provides
    public LoginService provideLoginService(){
        return new LoginServiceImpl();
    }
}

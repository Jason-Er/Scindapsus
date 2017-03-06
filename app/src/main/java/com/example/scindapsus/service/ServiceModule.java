package com.example.scindapsus.service;

import com.example.scindapsus.service.login.LoginService;
import com.example.scindapsus.service.login.LoginServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/3/2017.
 */
@Module
public class ServiceModule {
    @Singleton
    @Provides
    public LoginService provideLoginService(){
        return new LoginServiceImpl();
    }
}

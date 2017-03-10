package com.example.scindapsus.service;

import android.app.Application;
import android.content.Context;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.service.login.LoginService;
import com.example.scindapsus.service.login.LoginServiceImpl;
import com.example.scindapsus.util.ServiceScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/3/2017.
 */
@Module
public class ServiceModule {
    @ServiceScope
    @Provides
    public LoginService provideLoginService(ApplicationComponent applicationComponent){
        return new LoginServiceImpl(applicationComponent);
    }
}

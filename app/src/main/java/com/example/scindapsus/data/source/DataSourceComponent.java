package com.example.scindapsus.data.source;

import com.example.scindapsus.service.login.LoginServiceImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ej on 3/3/2017.
 */
@Singleton
@Component(modules={DataSourceModule.class})
public interface DataSourceComponent {
    void inject(LoginServiceImpl loginService);
}

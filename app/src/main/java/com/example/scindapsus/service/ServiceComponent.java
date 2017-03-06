package com.example.scindapsus.service;

import com.example.scindapsus.vp.login.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ej on 3/3/2017.
 */
@Singleton
@Component(modules={ServiceModule.class})
public interface ServiceComponent {
    void inject(LoginPresenter presenter);
}

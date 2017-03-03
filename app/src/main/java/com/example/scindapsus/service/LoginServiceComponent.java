package com.example.scindapsus.service;

import com.example.scindapsus.vp.login.LoginContract;
import com.example.scindapsus.vp.login.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ej on 3/3/2017.
 */
@Singleton
@Component(modules={LoginServiceModule.class})
public interface LoginServiceComponent {
    void inject(LoginPresenter presenter);
}

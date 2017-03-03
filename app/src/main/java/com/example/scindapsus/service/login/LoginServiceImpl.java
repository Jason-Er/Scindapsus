package com.example.scindapsus.service.login;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.HttpMethods;
import com.example.scindapsus.model.Token;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ej on 2/28/2017.
 */

public class LoginServiceImpl implements LoginService {
    @Inject HttpMethods httpMethods;

    public LoginServiceImpl() {
        DaggerDataSourceComponent.builder().build().inject(this);
    }
    @Override
    public void login(Subscriber<Token> subscriber, String name, String password) {
        System.out.println("Invoke LoginServiceImpl login!");
        httpMethods.login(subscriber, name, password);
    }
}

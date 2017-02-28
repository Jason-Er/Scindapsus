package com.example.scindapsus.service.remote;

import com.example.scindapsus.data.source.remote.HttpMethods;
import com.example.scindapsus.model.HttpResult;
import com.example.scindapsus.model.Token;
import com.example.scindapsus.service.LoginService;

import rx.Subscriber;

/**
 * Created by ej on 2/28/2017.
 */

public class LoginServiceImpl implements LoginService {
    @Override
    public void login(Subscriber<HttpResult<Token>> subscriber, String name, String password) {
        HttpMethods.getInstance().login(subscriber, name, password);
    }
}

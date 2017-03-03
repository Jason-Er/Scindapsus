package com.example.scindapsus.service.login;

import com.example.scindapsus.data.source.remote.HttpMethods;
import com.example.scindapsus.model.Token;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ej on 2/28/2017.
 */

public interface LoginService {
    void login(Subscriber<Token> subscriber, String name, String password);
}

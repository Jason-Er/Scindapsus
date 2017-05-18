package com.example.scindapsus.service.login;

import com.example.scindapsus.model.Token;

import org.reactivestreams.Subscriber;

/**
 * Created by ej on 2/28/2017.
 */

public interface LoginService {
    void login(Subscriber<Token> observer, String name, String password);
}

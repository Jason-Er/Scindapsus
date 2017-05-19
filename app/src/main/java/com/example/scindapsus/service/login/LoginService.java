package com.example.scindapsus.service.login;

import com.example.scindapsus.model.Token;

import io.reactivex.Observer;

/**
 * Created by ej on 2/28/2017.
 */

public interface LoginService {
    void login(Observer<Token> observer, String name, String password);
}

package com.example.scindapsus.data.source;

import com.example.scindapsus.model.Token;
import com.example.scindapsus.service.LoginService;

import rx.Subscriber;

/**
 * Created by ej on 2/22/2017.
 */

public class LoginRepository implements LoginService {

    private static LoginRepository INSTANCE = null;
    private static LoginService loginService; // remote.loginServiceImpl

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }

    @Override
    public void login(Subscriber<Token> subscriber, String name, String password) {

    }
}

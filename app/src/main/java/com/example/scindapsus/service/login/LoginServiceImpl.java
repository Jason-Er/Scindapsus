package com.example.scindapsus.service.login;

import android.util.Log;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.login.LoginHttpImpl;
import com.example.scindapsus.model.Token;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ej on 2/28/2017.
 */

public class LoginServiceImpl implements LoginService {
    final String TAG = "LoginServiceImpl";
    @Inject LoginHttpImpl loginHttpImpl;

    public LoginServiceImpl() {
        DaggerDataSourceComponent.builder().build().inject(this);
    }
    @Override
    public void login(Subscriber<Token> subscriber, String name, String password) {
        Log.i(TAG, "Invoke LoginServiceImpl login!");
        loginHttpImpl.login(subscriber, name, password);
    }
}

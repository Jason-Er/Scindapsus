package com.example.scindapsus.service.login;

import android.util.Log;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.login.LoginHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Token;

import org.reactivestreams.Subscriber;

import javax.inject.Inject;

/**
 * Created by ej on 2/28/2017.
 */

public class LoginServiceImpl implements LoginService {
    final String TAG = LoginServiceImpl.class.getName();
    @Inject LoginHttpImpl loginHttpImpl;

    public LoginServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }
    @Override
    public void login(Subscriber<Token> observer, String name, String password) {
        Log.i(TAG, "Invoke LoginServiceImpl login!");
        loginHttpImpl.login(observer, name, password);
    }
}

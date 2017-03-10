package com.example.scindapsus.data.source.remote.login;

import android.app.Application;
import android.util.Log;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Token;

import java.util.Properties;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ej on 3/6/2017.
 */

public class LoginHttpImpl {
    final static String TAG = "LoginHttpImpl";

    @Inject Retrofit retrofit;
    private LoginHttp loginHttp;

    public LoginHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        loginHttp = retrofit.create(LoginHttp.class);
    }

    public void login(Subscriber<Token> subscriber, String name, String password){
        Log.i(TAG,"Invoke method login");
        loginHttp.login(name, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

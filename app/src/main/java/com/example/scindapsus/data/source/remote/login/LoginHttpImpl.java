package com.example.scindapsus.data.source.remote.login;

import android.util.Log;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Auth;
import com.example.scindapsus.model.Token;
import com.example.scindapsus.model.adapter.AuthAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ej on 3/6/2017.
 */

public class LoginHttpImpl {
    final static String TAG = "LoginHttpImpl";

    @Inject RetrofitUtil retrofitUtil;
    private LoginHttp loginHttp;

    public LoginHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(AuthAdapterFactory.create()).create();
        loginHttp = retrofitUtil.createApi(LoginHttp.class, gson);
    }

    public void login(Subscriber<Token> subscriber, String name, String password){
        Log.i(TAG,"Invoke method login");
        loginHttp.login(Auth.newInstance(name, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

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

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import retrofit2.Response;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ej on 3/6/2017.
 */

public class LoginHttpImpl {
    final static String TAG = "LoginHttpImpl";

    @Inject Properties properties;
    @Inject RetrofitUtil retrofitUtil;
    private LoginHttp loginHttp;

    public LoginHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(AuthAdapterFactory.create()).create();
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        loginHttp = retrofitUtil.createApi(LoginHttp.class, gson);
    }

    public void login(Subscriber<Token> subscriber, String name, String password){
        Log.i(TAG,"Invoke method login");
        loginHttp.login(Auth.newInstance(name, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Void>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG,"Invoke method login token");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,"Invoke method login token:"+e.getMessage());
                    }

                    @Override
                    public void onNext(Response<Void> response) {
                        String tokenPrefix = properties.getProperty("TOKEN_PREFIX");
                        String authHeaderKey = properties.getProperty("AUTH_HEADER_KEY");
                        String token = response.headers().get(authHeaderKey);
                        String[] arr = token.split(tokenPrefix+" ");
                        Token.newInstance(arr[1]);
                        Log.i(TAG,"Invoke method login token:"+token);
                    }
                });
    }
}

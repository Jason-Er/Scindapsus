package com.example.scindapsus.data.source.remote.login;

import android.util.Log;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Auth;
import com.example.scindapsus.model.Token;
import com.example.scindapsus.model.adapter.CustomAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Properties;

import javax.inject.Inject;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


/**
 * Created by ej on 3/6/2017.
 */

public class LoginHttpImpl {
    final static String TAG = LoginHttpImpl.class.getName();

    @Inject
    Properties properties;
    @Inject
    RetrofitUtil retrofitUtil;

    private LoginHttp loginHttp;

    public LoginHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CustomAdapterFactory.create()).create();
        loginHttp = retrofitUtil.createApi(LoginHttp.class, gson);
    }

    public Observable<Token> login(String name, String password) {
        Log.i(TAG, "Invoke method login");
        return loginHttp.login(Auth.newInstance(name, password))
                .map(new Function<Response<Void>, Token>() {
                    @Override
                    public Token apply(@NonNull Response<Void> voidResponse) throws Exception {
                        String tokenPrefix = properties.getProperty("TOKEN_PREFIX");
                        String authHeaderKey = properties.getProperty("AUTH_HEADER_KEY");
                        String token = voidResponse.headers().get(authHeaderKey);
                        String[] arr = token.split(tokenPrefix + " ");
                        return Token.newInstance(arr[1]);
                    }
                });
    }
}

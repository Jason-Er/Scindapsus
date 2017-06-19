package com.example.scindapsus.data.source.remote.participate;

import android.util.Log;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.adapter.CustomAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Properties;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ej on 5/3/2017.
 */

public class ParticipateHttpImpl {

    final static String TAG =  ParticipateHttpImpl.class.getName();

    @Inject
    RetrofitUtil retrofitUtil;
    @Inject
    Properties properties;

    private ParticipateHttp participateHttp;

    public ParticipateHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CustomAdapterFactory.create()).create();
        participateHttp = retrofitUtil.createApi(ParticipateHttp.class, gson);
    }

    public Observable<Play> loadPlay(String token, int id) {
        Log.i(TAG, "loadPlay");
        return participateHttp.loadPlay(token, id);
    }
}

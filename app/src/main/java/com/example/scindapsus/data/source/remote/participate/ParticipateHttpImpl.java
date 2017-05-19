package com.example.scindapsus.data.source.remote.participate;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Play;

import java.util.Properties;

import javax.inject.Inject;

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
        participateHttp = retrofitUtil.createApi(ParticipateHttp.class);
    }

    public void loadPlay(String token, Observer<Play> observer, int id) {
        participateHttp.loadPlay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

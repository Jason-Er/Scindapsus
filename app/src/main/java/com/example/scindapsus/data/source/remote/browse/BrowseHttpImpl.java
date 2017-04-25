package com.example.scindapsus.data.source.remote.browse;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Token;

import java.util.Properties;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ej on 4/25/2017.
 */

public class BrowseHttpImpl {
    @Inject
    Properties properties;
    @Inject
    RetrofitUtil retrofitUtil;
    private BrowseHttp browseHttp;

    public BrowseHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    public void loadPlaysInfo(Subscriber<Token> subscriber, int page) {

    }
}

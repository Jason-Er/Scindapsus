package com.example.scindapsus.data.source.remote.browse;

import com.example.scindapsus.data.source.remote.DaggerHttpComponent;
import com.example.scindapsus.data.source.remote.RetrofitUtil;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;
import com.example.scindapsus.service.shared.SharedService;


import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ej on 4/25/2017.
 */

public class BrowseHttpImpl {
    final static String TAG = BrowseHttpImpl.class.getName();
    @Inject
    RetrofitUtil retrofitUtil;
    @Inject
    Properties properties;
    /*
    @Inject
    SharedService sharedService;
    */
    private BrowseHttp browseHttp;

    public BrowseHttpImpl(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
        browseHttp = retrofitUtil.createApi(BrowseHttp.class);
    }

    public void loadPlaysInfo(String token, Observer<PageResult<List<PlayInfo>>> observer, int page, int size) {
        //String token = sharedService.getToken();
        //String token = "";
        browseHttp.loadPlaysInfo(token)//loadPlaysInfo(token, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

    public Observable<PageResult<List<PlayInfo>>> loadPlaysInfo(String token, int page) {
        return browseHttp.loadPlaysInfo(token);
    }

}

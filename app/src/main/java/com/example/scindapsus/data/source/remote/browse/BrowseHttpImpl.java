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

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    public void loadPlaysInfo(String token, Subscriber<List<PlayInfo>> subscriber, int page, int size) {
        //String token = sharedService.getToken();
        //String token = "";
        browseHttp.loadPlaysInfo(token) //loadPlaysInfo(token, page, size)
                .map(new Func1<PageResult<List<PlayInfo>>, List<PlayInfo>>() {
                    @Override
                    public List<PlayInfo> call(PageResult<List<PlayInfo>> pageResult) {
                        return pageResult.getContent();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void loadPlaysInfo(String token, Subscriber<List<PlayInfo>> subscriber, int page) {
        loadPlaysInfo(token, subscriber, page, Integer.parseInt(properties.getProperty("DEFAULT_PAGE_SIZE")));
    }
}

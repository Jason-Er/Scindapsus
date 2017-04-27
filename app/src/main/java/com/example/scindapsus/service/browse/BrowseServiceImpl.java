package com.example.scindapsus.service.browse;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.browse.BrowseHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.PlayInfo;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ej on 4/25/2017.
 */

public class BrowseServiceImpl implements BrowseService {
    @Inject BrowseHttpImpl browseHttpImpl;
    public BrowseServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }
    @Override
    public void loadPlaysInfo(String token, Subscriber<List<PlayInfo>> subscriber, int page) {
        browseHttpImpl.loadPlaysInfo(token, subscriber,page);
    }
}

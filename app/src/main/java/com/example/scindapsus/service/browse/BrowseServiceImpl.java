package com.example.scindapsus.service.browse;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.browse.BrowseHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ej on 4/25/2017.
 */

public class BrowseServiceImpl implements BrowseService {

    @Inject
    BrowseHttpImpl browseHttpImpl;

    public BrowseServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public Observable<PageResult<List<PlayInfo>>> loadPlaysInfo(String token, int page) {
        return browseHttpImpl.loadPlaysInfo(token, page);
    }
}

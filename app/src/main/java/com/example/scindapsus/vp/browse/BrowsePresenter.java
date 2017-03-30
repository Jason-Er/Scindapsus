package com.example.scindapsus.vp.browse;


import android.support.annotation.NonNull;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.service.DaggerServiceComponent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class BrowsePresenter implements BrowseContract.Presenter{

    private final BrowseContract.View mBrowseView;

    @Override
    public void start() {

    }

    public BrowsePresenter(@NonNull BrowseContract.View browseView, @NonNull ApplicationComponent applicationComponent) {
        mBrowseView = checkNotNull(browseView, "browseView cannot be null!");
        mBrowseView.setPresenter(this);
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);

    }
}

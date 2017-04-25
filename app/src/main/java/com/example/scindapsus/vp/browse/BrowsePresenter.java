package com.example.scindapsus.vp.browse;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Token;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.browse.BrowseService;
import com.example.scindapsus.service.shared.SharedService;

import javax.inject.Inject;

import rx.Subscriber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class BrowsePresenter implements BrowseContract.Presenter{

    private final String TAG = BrowsePresenter.class.getName();

    private final BrowseContract.View mBrowseView;
    private boolean mFirstLoad = true;

    @Inject
    BrowseService browseService;
    @Inject
    SharedService sharedService;

    public BrowsePresenter(@NonNull BrowseContract.View browseView, @NonNull ApplicationComponent applicationComponent) {
        mBrowseView = checkNotNull(browseView, "browseView cannot be null!");
        mBrowseView.setPresenter(this);
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);

    }

    @Override
    public void subscribe() {
        loadPlaysInfo(false);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadPlaysInfo(boolean forceUpdate) {
        loadPlaysInfo(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadPlaysInfo(final boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mBrowseView.setLoadingIndicator(true);
        }

        Subscriber subscriber = new Subscriber<Token>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Token token) {
                Log.i(TAG, "onNext");

            }
        };

        browseService.loadPlaysInfo(subscriber, 0);
    }
}

package com.example.scindapsus.vp.browse;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.browse.BrowseService;
import com.example.scindapsus.service.image.ImageService;
import com.example.scindapsus.service.shared.SharedService;
import com.example.scindapsus.util.bus.RxBus;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class BrowsePresenter implements BrowseContract.Presenter{

    private final String TAG = BrowsePresenter.class.getName();

    private final BrowseContract.View mBrowseView;
    private boolean mFirstLoad = true;
    private Subscription rxSubscription;
    private PlayInfo playInfoOnClick;

    @Inject
    BrowseService browseService;
    @Inject
    ImageService imageService;
    @Inject
    SharedService sharedService;

    public BrowsePresenter(@NonNull BrowseContract.View browseView, @NonNull ApplicationComponent applicationComponent) {
        mBrowseView = checkNotNull(browseView, "browseView cannot be null!");
        mBrowseView.setPresenter(this);
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);

        rxSubscription = RxBus.getDefault().toObservable(PlayInfo.class)
                .subscribe(new Action1<PlayInfo>() {
                               @Override
                               public void call(PlayInfo playInfo) {
                                   Log.i(TAG, "playInfo.name: "+playInfo.getName());
                                   playInfoOnClick = playInfo;
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                // exception
                            }
                        });
    }

    @Override
    public void subscribe() {
        loadPlaysInfo(false);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void onDestroy() {
        if(!rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }

    @Override
    public void loadPlaysInfo(boolean forceUpdate) {
        loadPlaysInfo(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    @Override
    public void recyclerViewItemClick(View view, int position) {

        mBrowseView.navigateToParticipate(playInfoOnClick);

    }

    private void loadPlaysInfo(final boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mBrowseView.setLoadingIndicator(true);
        }

        Subscriber subscriber = new Subscriber<PageResult<List<PlayInfo>>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");

            }

            @Override
            public void onNext(PageResult<List<PlayInfo>> pageResult) {
                Log.i(TAG, "onNext");
                processPlaysInfo(pageResult);

            }

        };

        browseService.loadPlaysInfo(sharedService.getToken(), subscriber, 0);
    }

    private void processPlaysInfo(@NonNull PageResult<List<PlayInfo>> pageResult) {
        List<PlayInfo> playsInfo = pageResult.getContent();
        if (playsInfo.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            processEmptyPlaysInfo();
        } else {
            mBrowseView.showPlaysInfo(playsInfo);
        }
    }

    private void processEmptyPlaysInfo() {

    }
}

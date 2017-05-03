package com.example.scindapsus.vp.participate;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.util.bus.RxBus;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.SynchronousQueue;

import rx.Subscription;
import rx.functions.Action1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class ParticipatePresenter implements ParticipateContract.Presenter{

    private static String TAG = ParticipatePresenter.class.getName();
    private final ParticipateContract.View mParticipateView;
    private boolean mFirstLoad = true;

    public ParticipatePresenter(@NonNull ParticipateContract.View participateView, @NonNull ApplicationComponent applicationComponent) {
        mParticipateView = checkNotNull(participateView, "participateView cannot be null!");
        mParticipateView.setPresenter(this);
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void subscribe(PlayInfo playInfo) {
        loadPlay(false, playInfo.getId());
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadPlay(boolean forceUpdate, int id) {
        loadPlay(forceUpdate || mFirstLoad, true, id);
        mFirstLoad = false;
    }

    private void loadPlay(final boolean forceUpdate, final boolean showLoadingUI, int id) {
        if (showLoadingUI) {
            mParticipateView.setLoadingIndicator(true);
        }
    }
}

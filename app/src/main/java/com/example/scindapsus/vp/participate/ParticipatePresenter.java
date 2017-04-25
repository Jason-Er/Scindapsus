package com.example.scindapsus.vp.participate;


import android.support.annotation.NonNull;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.service.DaggerServiceComponent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class ParticipatePresenter implements ParticipateContract.Presenter{

    private final ParticipateContract.View mParticipateView;

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
    public void unsubscribe() {

    }
}

package com.example.scindapsus.vp.participate;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.participate.ParticipateService;
import com.example.scindapsus.service.shared.SharedService;
import com.example.scindapsus.vp.participate.scene.ScenePresenter;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class ParticipatePresenter implements ParticipateContract.Presenter{

    private static String TAG = ParticipatePresenter.class.getName();
    private final ParticipateContract.View mParticipateView;
    private boolean mFirstLoad = true;
    private ScenePresenter scenePresenter;
    private int currentScene = 0;

    @Inject
    ParticipateService participateService;
    @Inject
    SharedService sharedService;

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

        MaybeObserver observer = new MaybeObserver<Play>() {

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onSuccess(@io.reactivex.annotations.NonNull Play play) {
                scenePresenter.setPlayUid(play.getName()+play.getId());
                scenePresenter.setScene(play.getScenes().get(currentScene));
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };

        participateService.loadPlay(sharedService.getToken(), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public ScenePresenter getScenePresenter() {
        return scenePresenter;
    }

    @Override
    public void setScenePresenter(ScenePresenter scenePresenter) {
        this.scenePresenter = scenePresenter;
    }
}

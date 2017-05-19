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

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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

        Observer observer = new Observer<Play>() {
            @Override
            public void onComplete() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");

            }

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(Play play) {
                Log.i(TAG, "onNext");
                scenePresenter.setScene(play.getScenes().get(currentScene));

            }
        };

        participateService.loadPlay(sharedService.getToken(), observer, id);
    }

    public ScenePresenter getScenePresenter() {
        return scenePresenter;
    }

    @Override
    public void setScenePresenter(ScenePresenter scenePresenter) {
        this.scenePresenter = scenePresenter;
    }
}

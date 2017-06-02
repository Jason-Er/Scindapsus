package com.example.scindapsus.vp.participate.scene;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.LineM;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.scene.SceneService;
import com.example.scindapsus.service.shared.SharedService;
import com.example.scindapsus.util.common.FileUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.List;


import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by ej on 5/4/2017.
 */

public class ScenePresenter implements SceneContract.Presenter {

    final static String TAG = ScenePresenter.class.getName();

    private Scene mScene;
    private Context context;
    private final SceneContract.View mSceneView;

    private Subscription downloadRequestsSubscription;
    private final int DOWNLOAD_AUDIO_NUM = 1;

    @Inject
    SceneService sceneService;
    @Inject
    SharedService sharedService;

    public ScenePresenter(@NonNull SceneContract.View view, @NonNull ApplicationComponent applicationComponent) {
        mSceneView = view;
        mSceneView.setPresenter(this);

        context = applicationComponent.getApplication().getBaseContext();
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    public Scene getScene() {
        return mScene;
    }

    public void setScene(Scene scene) {
        this.mScene = scene;
        mSceneView.showLines(scene.getLines());
        loadLinesAudio(scene.getLines());
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    private void loadLinesAudio(List<Line> lines) {
        // TODO: 5/22/2017 path4Save need refactoring according to the user login
        final String path4Save = context.getFilesDir().getAbsolutePath();
        final String token = sharedService.getToken();

        final Observer<LineM> lineMObserver = new Observer<LineM>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull LineM lineM) {
                Log.i(TAG, lineM.toString());
                downloadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };

        Subscriber subscriber = new Subscriber<Line>() {

            @Override
            public void onSubscribe(Subscription s) {
                Log.i(TAG, "onSubscribe");
                downloadRequestsSubscription = s;
            }

            @Override
            public void onNext(Line line) {
                Log.i(TAG, "onNext");
                FileUtil.downloadOneAudio(sceneService, token, line, path4Save)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(lineMObserver);

            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };
        Flowable flowable = Flowable.fromIterable(lines);
        flowable.subscribe(subscriber);
        downloadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);

    }
}

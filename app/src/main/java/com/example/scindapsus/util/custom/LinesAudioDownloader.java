package com.example.scindapsus.util.custom;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.model.Line;
import com.example.scindapsus.service.scene.SceneService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InputStream;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ej on 5/19/2017.
 */

public class LinesAudioDownloader {
    private final String TAG = LinesAudioDownloader.class.getName();
    private Subscription downloadRequestsSubscription;
    private final Flowable flowable;
    private final int DOWNLOAD_AUDIO_NUM = 1;
    private final SceneService sceneService;
    private final String token;

    final Observer<InputStream> observer = new Observer<InputStream>() {
        @Override
        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {
            Log.i(TAG, "onSubscribe");
        }

        @Override
        public void onNext(@io.reactivex.annotations.NonNull InputStream inputStream) {
            // TODO: 5/19/2017 save inputStream to local disk

            Log.i(TAG, "onNext");
            // after complete
            downloadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);
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

    final Subscriber subscriber = new Subscriber<Line>() {

        @Override
        public void onSubscribe(Subscription s) {
            Log.i(TAG, "onSubscribe");
            downloadRequestsSubscription = s;
        }

        @Override
        public void onNext(Line line) {
            Log.i(TAG, "onNext");
            Log.i(TAG, "line :" + line.getText());
            sceneService.loadAudio(token, observer, line.getAudioURL());
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

    public LinesAudioDownloader(@NonNull SceneService sceneService, @NonNull String token, @NonNull final List<Line> lines) {
        this.token = token;
        this.sceneService = sceneService;
        flowable = Flowable.fromIterable(lines);
        flowable.subscribeWith(subscriber);
    }

    public void startDownload() {
        downloadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (downloadRequestsSubscription != null) {
            downloadRequestsSubscription.cancel();
        }
    }
}

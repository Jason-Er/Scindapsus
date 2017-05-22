package com.example.scindapsus.util.common;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.model.Line;
import com.example.scindapsus.service.scene.SceneService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

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
    private final String path;

    final Observer<File> fileObserver = new Observer<File>() {
        @Override
        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

        }

        @Override
        public void onNext(@io.reactivex.annotations.NonNull File file) {
            Log.d(TAG, "File downloaded to " + file.getAbsolutePath());
        }

        @Override
        public void onError(@io.reactivex.annotations.NonNull Throwable e) {
            e.printStackTrace();
            Log.d(TAG, "Error " + e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onCompleted");
        }
    };

    final Observer<Response<ResponseBody>> observer = new Observer<Response<ResponseBody>>() {
        @Override
        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {
            Log.i(TAG, "onSubscribe");
        }

        @Override
        public void onNext(@io.reactivex.annotations.NonNull Response<ResponseBody> responseBodyResponse) {
            Log.i(TAG, "onNext");
            FileUtil.saveToDiskRx(responseBodyResponse, path)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(fileObserver);
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

    public LinesAudioDownloader(@NonNull SceneService sceneService, @NonNull String token, @NonNull final List<Line> lines, @NonNull String path) {
        this.path = path;
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

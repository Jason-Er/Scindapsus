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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
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


    final Subscriber subscriber = new Subscriber<File>() {

        @Override
        public void onSubscribe(Subscription s) {
            Log.i(TAG, "onSubscribe");
            downloadRequestsSubscription = s;
        }

        @Override
        public void onNext(File file) {
            Log.i(TAG, "onNext");
            downloadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);
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

    public LinesAudioDownloader(final @NonNull SceneService sceneService, @NonNull final String token, @NonNull final List<Line> lines, final @NonNull String path) {
        flowable = Flowable.fromIterable(lines);
        flowable.flatMap(new Function<Line, Observable<Response<ResponseBody>>>() {
            @Override
            public Observable<Response<ResponseBody>> apply(@io.reactivex.annotations.NonNull Line line) throws Exception {
                return sceneService.loadAudio(token, line.getAudioURL());
            }
        }).flatMap(new Function<Response<ResponseBody>, Observable<File>>() {
            @Override
            public Observable<File> apply(@io.reactivex.annotations.NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
                return FileUtil.saveToDiskRx(responseBodyResponse, path);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber);
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

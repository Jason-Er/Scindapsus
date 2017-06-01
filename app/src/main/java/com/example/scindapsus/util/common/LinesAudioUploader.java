package com.example.scindapsus.util.common;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.model.LineM;
import com.example.scindapsus.model.UploadAudioUrl;
import com.example.scindapsus.service.scene.SceneService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by ej on 5/19/2017.
 */

public class LinesAudioUploader {
    private final String TAG = LinesAudioUploader.class.getName();
    private Subscription uploadRequestsSubscription;
    private final Flowable flowable;
    private final int DOWNLOAD_AUDIO_NUM = 1;
    private final String token;

    final Subscriber subscriber = new Subscriber<UploadAudioUrl>() {

        @Override
        public void onSubscribe(Subscription s) {
            Log.i(TAG, "onSubscribe");
            uploadRequestsSubscription = s;
        }

        @Override
        public void onNext(UploadAudioUrl line) {
            Log.i(TAG, "onNext");
            // TODO: 6/1/2017 save url to line url
            uploadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);
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

    public LinesAudioUploader(final @NonNull SceneService sceneService, @NonNull final String token, @NonNull final List<LineM> lines) {
        this.token = token;
        flowable = Flowable.fromIterable(lines);
        flowable.flatMap(new Function<LineM, Observable<UploadAudioUrl>>() {
            @Override
            public Observable<UploadAudioUrl> apply(@io.reactivex.annotations.NonNull LineM lineM) throws Exception {
                return FileUtil.getUploadFileInfo(Uri.parse(lineM.audiourl_local()), "audio/3gpp")
                        .flatMap(new Function<FileUtil.UploadFileInfo, Observable<UploadAudioUrl>>() {
                            @Override
                            public Observable<UploadAudioUrl> apply(@io.reactivex.annotations.NonNull FileUtil.UploadFileInfo uploadFileInfo) throws Exception {
                                return sceneService.uploadAudio(token, uploadFileInfo.description, uploadFileInfo.body);
                            }
                        });
            }
        }).subscribeWith(subscriber);
    }

    public void startUpload() {
        uploadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (uploadRequestsSubscription != null) {
            uploadRequestsSubscription.cancel();
        }
    }
}

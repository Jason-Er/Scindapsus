package com.example.scindapsus.vp.participate.scene;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.Role;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.model.Voice;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.scene.SceneService;
import com.example.scindapsus.service.shared.SharedService;
import com.example.scindapsus.util.common.FileUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by ej on 5/4/2017.
 */

public class ScenePresenter implements SceneContract.Presenter {

    final static String TAG = ScenePresenter.class.getName();

    private String playUid;
    private Scene mScene;
    private Context context;
    private final SceneContract.View mSceneView;

    private Subscription downloadRequestsSubscription;
    private Subscription uploadRequestsSubscription;
    private List<Line> lines = new ArrayList<>();

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
        mSceneView.showLines(scene.lines());
        loadLinesAudio(scene.lines());
    }

    public String getPlayUid() {
        return playUid;
    }

    public void setPlayUid(String playUid) {
        this.playUid = playUid;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    private void loadLinesAudio(List<Line> lines) {
        // final String path4Save = context.getFilesDir().getAbsolutePath() + "/" + sharedService.getUserName() + "/" + playUid + "/" + "scene" + mScene.getOrdinal();
        final String path4Save = context.getFilesDir().getAbsolutePath() + "/" + playUid + "/" + "scene" + mScene.ordinal();
        final String token = sharedService.getToken();

        final Observer<Voice> lineObserver = new Observer<Voice>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Voice voice) {
                Log.i(TAG, voice.toString());
                // ScenePresenter.this.lines.add(voice);
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
                downloadOneAudio(sceneService, token, line, path4Save)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(lineObserver);

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

    private void uploadLinesAudio(List<Line> lines) {
        final String token = sharedService.getToken();

        final Observer<Voice> voiceObserver = new Observer<Voice>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Voice voice) {
                Log.i(TAG, voice.toString());
                uploadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);
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
                uploadRequestsSubscription = s;
            }

            @Override
            public void onNext(Line line) {
                Log.i(TAG, "onNext");
                uploadOneAudio(sceneService, token, playUid, line)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(voiceObserver);
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
        uploadRequestsSubscription.request(DOWNLOAD_AUDIO_NUM);

    }

    @Override
    public void uploadToServer() {
        uploadLinesAudio(lines);
    }

    public static Observable<Voice> downloadOneAudio(@io.reactivex.annotations.NonNull final SceneService sceneService, @io.reactivex.annotations.NonNull final String token, @io.reactivex.annotations.NonNull final Line line, @io.reactivex.annotations.NonNull final String path) {
        return Observable.zip(sceneService
                        .loadAudio(token, line.audio_url())
                        .flatMap(new Function<Response<ResponseBody>, Observable<File>>() {
                            @Override
                            public Observable<File> apply(@io.reactivex.annotations.NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
                                return FileUtil.saveToDiskRx(responseBodyResponse, path);
                            }
                        })
                        .map(new Function<File, Voice>() {
                            @Override
                            public Voice apply(@io.reactivex.annotations.NonNull File file) throws Exception {
                                return Voice.create(0, line.audio_url(), file.getAbsolutePath(), 0, line.id());
                            }
                        }),
                sceneService.findRoleByRoleId(line.role_id()),
                new BiFunction<Voice, Role, Voice>() {
                    @Override
                    public Voice apply(@io.reactivex.annotations.NonNull Voice voice, @io.reactivex.annotations.NonNull Role role) throws Exception {
                        return Voice.create(voice.id(), voice.audiourl_server(), voice.audiourl_local(), role.user_id(), voice.line_id());
                    }
                })
                .flatMap(new Function<Voice, Observable<Voice>>() {
                    @Override
                    public Observable<Voice> apply(@io.reactivex.annotations.NonNull Voice voice) throws Exception {
                        return sceneService.saveVoice(voice);
                    }
                });
    }

    public static Observable<Voice> uploadOneAudio(@io.reactivex.annotations.NonNull final SceneService sceneService, @io.reactivex.annotations.NonNull final String token, @io.reactivex.annotations.NonNull final String playUid, @io.reactivex.annotations.NonNull final Line line ) {
        // TODO: 6/16/2017 need to save to another table
        /*
        File file = new File(lineM.audiourl_local());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file",file.getName(),requestFile);
        return sceneService.uploadOneAudio(token, requestFile, multipartBody, playUid, lineM)
                .flatMap(new Function<UploadAudioUrl, Observable<LineM>>() {
                    @Override
                    public Observable<LineM> apply(@io.reactivex.annotations.NonNull UploadAudioUrl uploadAudioUrl) throws Exception {
                        return sceneService.saveLineM(LineM.create(lineM.id(),lineM.text(),uploadAudioUrl.getUrl(),lineM.audiourl_local(),lineM.ordinal(),lineM.scene_id()));
                    }
                });
        */
        return null;
    }
}

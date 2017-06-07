package com.example.scindapsus.util.common;

import android.net.Uri;
import android.util.Log;

import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.LineM;
import com.example.scindapsus.model.UploadAudioUrl;
import com.example.scindapsus.service.scene.SceneService;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 * Created by ej on 5/19/2017.
 */

public class FileUtil {
    private static final String TAG = FileUtil.class.getName();

    public static Observable<File> saveToDiskRx(final Response<ResponseBody> response, final String path) {
        return Observable.create(new ObservableOnSubscribe(){
            @Override
            public void subscribe(@NonNull ObservableEmitter observableEmitter) throws Exception {
                String header = response.headers().get("Content-Disposition");
                String filename = header.replace("attachment; filename=", "");

                new File(path).mkdirs();
                File destinationFile = new File(path, filename);

                BufferedSink bufferedSink = Okio.buffer(Okio.sink(destinationFile));
                bufferedSink.writeAll(response.body().source());
                bufferedSink.close();

                observableEmitter.onNext(destinationFile);
                observableEmitter.onComplete();
            }
        });
    }

    public static Observable<LineM> downloadOneAudio(@NonNull final SceneService sceneService, @NonNull final String token, @NonNull final Line line, @NonNull final String path) {
        return sceneService
                .loadAudio(token, line.getAudioURL())
                .flatMap(new Function<Response<ResponseBody>, Observable<File>>() {
                    @Override
                    public Observable<File> apply(@NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
                        return FileUtil.saveToDiskRx(responseBodyResponse, path);
                    }
                }).map(new Function<File, String>() {
                    @Override
                    public String apply(@NonNull File file) throws Exception {
                        return file.getAbsolutePath();
                    }
                })
                .flatMap(new Function<String, Observable<LineM>>() {
                    @Override
                    public Observable<LineM> apply(@NonNull String s) throws Exception {
                        return Observable.just(LineM.create(line.getId(), line.getText(), line.getAudioURL(), s, line.getOrdinal(), line.getSceneId()));
                    }
                });
    }

    public static Observable<LineM> uploadOneAudio(@NonNull final SceneService sceneService, @NonNull final String token, @NonNull final String playUid, @NonNull final LineM lineM ) {
        File file = new File(lineM.audiourl_local());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file",file.getName(),requestFile);
        return sceneService.uploadOneAudio(token, requestFile, multipartBody, playUid, lineM)
                .flatMap(new Function<UploadAudioUrl, Observable<LineM>>() {
                    @Override
                    public Observable<LineM> apply(@NonNull UploadAudioUrl uploadAudioUrl) throws Exception {
                        return Observable.just(LineM.create(lineM.id(),lineM.text(),uploadAudioUrl.getUrl(),lineM.audiourl_local(),lineM.ordinal(),lineM.scene_id()));
                    }
                });
    }
}

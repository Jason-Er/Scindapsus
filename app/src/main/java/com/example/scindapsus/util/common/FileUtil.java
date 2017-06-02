package com.example.scindapsus.util.common;

import android.net.Uri;

import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.LineM;
import com.example.scindapsus.service.scene.SceneService;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

                new File(path).mkdir();
                File destinationFile = new File(path, filename);

                BufferedSink bufferedSink = Okio.buffer(Okio.sink(destinationFile));
                bufferedSink.writeAll(response.body().source());
                bufferedSink.close();

                observableEmitter.onNext(destinationFile);
                observableEmitter.onComplete();
            }
        });
    }


    public static class UploadFileInfo {
        RequestBody description;
        MultipartBody.Part body;

        public UploadFileInfo(RequestBody description, MultipartBody.Part body) {
            this.description = description;
            this.body = body;
        }
    }

    public static Observable<UploadFileInfo> getUploadFileInfo(final Uri fleUri, final String fileType) {
        return Observable.create(new ObservableOnSubscribe<UploadFileInfo>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<UploadFileInfo> observableEmitter) throws Exception {
                File file = new File(fleUri.getPath());
                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(fileType),
                                file
                        );
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
                String descriptionString = "hello, this is description speaking";
                RequestBody description =
                        RequestBody.create(
                                okhttp3.MultipartBody.FORM, descriptionString);
                UploadFileInfo uploadFileInfo = new UploadFileInfo(description, body);
                observableEmitter.onNext(uploadFileInfo);
                observableEmitter.onComplete();
            }
        });
    }

    public static Single<List<String>> downloadLinesAudio(final @NonNull SceneService sceneService, @NonNull final String token, @NonNull final List<Line> lines, final @NonNull String path) {
        Flowable flowable = Flowable.fromIterable(lines);
        return flowable.flatMap(new Function<Line, Observable<Response<ResponseBody>>>() {
            @Override
            public Observable<Response<ResponseBody>> apply(@NonNull Line line) throws Exception {
                return sceneService.loadAudio(token, line.getAudioURL());
            }
        }).flatMap(new Function<Response<ResponseBody>, Observable<File>>() {
            @Override
            public Observable<File> apply(@NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
                return FileUtil.saveToDiskRx(responseBodyResponse, path);
            }
        }).map(new Function<File, String>() {
            @Override
            public String apply(@NonNull File file) throws Exception {
                return file.getAbsolutePath();
            }
        }).toList();
    }

    public static Observable<LineM> downloadOneAudio(@NonNull final SceneService sceneService, @NonNull final String token, @NonNull final Line line, @NonNull final String path) {
        return sceneService
                .loadAudio(token, line.getAudioURL())
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
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
}

package com.example.scindapsus.util.common;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
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

}

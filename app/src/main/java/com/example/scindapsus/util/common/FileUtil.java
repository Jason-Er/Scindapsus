package com.example.scindapsus.util.common;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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


    public static boolean writeResponseBodyToDisk(InputStream inputStream, String path) {
        try {
            File futureStudioIconFile = new File(path + File.separator + "Future Studio Icon.png");


            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            }  finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean writeResponseBodyToDisk(ResponseBody body, String path) {
        try {
            File futureStudioIconFile = new File(path + File.separator + "Future Studio Icon.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            }  finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}

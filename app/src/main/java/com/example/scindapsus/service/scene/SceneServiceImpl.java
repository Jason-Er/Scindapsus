package com.example.scindapsus.service.scene;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.UploadAudioUrl;
import com.example.scindapsus.util.common.LinesAudioDownloader;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by ej on 5/4/2017.
 */

public class SceneServiceImpl implements SceneService {
    @Inject
    FileHttpImpl fileHttp;
    public SceneServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public void loadAudio(String token, Observer<Response<ResponseBody>> observer, String Url) {
        fileHttp.getFile(token, observer, Url);
    }

    @Override
    public Observable<UploadAudioUrl> uploadAudio(String token, RequestBody description, MultipartBody.Part body) {
        return fileHttp.uploadFile(token, description, body);
    }

    @Override
    public InputStream loadAudio(String token, String Url) {
        return null;
    }

    @Override
    public List<Line> loadAudio(String token, List<Line> lines, String path) {
        LinesAudioDownloader linesAudioDownloader = new LinesAudioDownloader(this, token, lines, path);
        linesAudioDownloader.startDownload();
        return null;
    }

}

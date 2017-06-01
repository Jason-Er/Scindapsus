package com.example.scindapsus.service.scene;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.LineM;
import com.example.scindapsus.model.UploadAudioUrl;
import com.example.scindapsus.util.common.LinesAudioDownloader;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    public Observable<Response<ResponseBody>> loadAudio(String token, String Url) {
        return fileHttp.getFile(token, Url);
    }

    @Override
    public Observable<UploadAudioUrl> uploadAudio(String token, RequestBody description, MultipartBody.Part body) {
        return fileHttp.uploadFile(token, description, body);
    }

    @Override
    public List<LineM> loadAudio(String token, List<Line> lines, String path) {
        LinesAudioDownloader linesAudioDownloader = new LinesAudioDownloader(this, token, lines, path);
        linesAudioDownloader.startDownload();
        return null;
    }

}

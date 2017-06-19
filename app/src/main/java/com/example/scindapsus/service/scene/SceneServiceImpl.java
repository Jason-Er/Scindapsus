package com.example.scindapsus.service.scene;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.local.scene.SceneImpl;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.model.UploadAudioUrl;

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
    SceneImpl sceneImpl;

    @Inject
    FileHttpImpl fileHttp;

    public SceneServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public Observable<Response<ResponseBody>> loadAudio(String token, String Url) {
        return fileHttp.downloadFile(token, Url);
    }

    @Override
    public Observable<UploadAudioUrl> uploadOneAudio(String token, RequestBody description, MultipartBody.Part body, String playUid, Line line) {
        return fileHttp.uploadOneAudio(token, description, body, playUid, String.valueOf(line.scene_id()), String.valueOf(line.id()));
    }

    @Override
    public Observable<Line> saveLine(Line line) {
        return sceneImpl.saveLine(line);
    }

}

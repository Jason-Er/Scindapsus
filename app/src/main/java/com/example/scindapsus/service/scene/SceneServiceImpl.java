package com.example.scindapsus.service.scene;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.local.scene.SceneImpl;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Role;
import com.example.scindapsus.model.UploadVoiceUrl;
import com.example.scindapsus.model.Voice;

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
    public Observable<UploadVoiceUrl> uploadOneAudio(final String token, final RequestBody description, final MultipartBody.Part body, final String playNameId, final String sceneOrdinal, final String lineOrdinal) {
        return fileHttp.uploadOneAudio(token, description, body, playNameId, sceneOrdinal, lineOrdinal);
    }

    @Override
    public Observable<Voice> saveVoice(Voice voice) {
        return sceneImpl.saveVoice(voice);
    }

    @Override
    public Observable<Role> findRoleByRoleId(Long roleId) {
        return sceneImpl.findRoleByRoleId(roleId);
    }

}

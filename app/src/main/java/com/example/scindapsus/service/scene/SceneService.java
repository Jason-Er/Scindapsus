package com.example.scindapsus.service.scene;

import com.example.scindapsus.model.Role;
import com.example.scindapsus.model.UploadVoiceUrl;
import com.example.scindapsus.model.Voice;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by ej on 5/4/2017.
 */

public interface SceneService {
    Observable<Response<ResponseBody>> loadAudio(String token, String Url);
    Observable<UploadVoiceUrl> uploadOneAudio(String token, RequestBody description, MultipartBody.Part body, final String playNameId, final String sceneOrdinal, final String lineOrdinal);
    Observable<Voice> saveVoice(Voice voice); // only save to local sqlite
    Observable<Role> findRoleByRoleId(Long roleId);
}

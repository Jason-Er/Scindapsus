package com.example.scindapsus.service.scene;

import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.LineM;
import com.example.scindapsus.model.UploadAudioUrl;

import java.util.List;

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
    Observable<UploadAudioUrl> uploadOneAudio(String token, RequestBody description, MultipartBody.Part body, String playUid, LineM lineM);
}

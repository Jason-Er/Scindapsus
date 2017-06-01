package com.example.scindapsus.service.scene;

import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.UploadAudioUrl;

import java.io.InputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by ej on 5/4/2017.
 */

public interface SceneService {
    void loadAudio(String token, Observer<Response<ResponseBody>> subscriber, String Url);
    Observable<UploadAudioUrl> uploadAudio(String token, RequestBody description, MultipartBody.Part body);
    InputStream loadAudio(String token, String Url);
    List<Line> loadAudio(String token, List<Line> lines, String path);
}

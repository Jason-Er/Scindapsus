package com.example.scindapsus.service.scene;

import com.example.scindapsus.model.Line;

import java.io.InputStream;
import java.util.List;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by ej on 5/4/2017.
 */

public interface SceneService {
    void loadAudio(String token, Observer<Response<ResponseBody>> subscriber, String Url);
    InputStream loadAudio(String token, String Url);
    List<Line> loadAudio(String token, List<Line> lines, String path);
}

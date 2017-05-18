package com.example.scindapsus.service.scene;

import java.io.InputStream;

import io.reactivex.Observer;


/**
 * Created by ej on 5/4/2017.
 */

public interface SceneService {
    void loadAudio(String token, Observer<InputStream> subscriber, String Url);
    InputStream loadAudio(String token, String Url);
}

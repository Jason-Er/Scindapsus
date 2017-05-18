package com.example.scindapsus.service.scene;

import org.reactivestreams.Subscriber;

import java.io.InputStream;


/**
 * Created by ej on 5/4/2017.
 */

public interface SceneService {
    void loadAudio(String token, Subscriber<InputStream> subscriber, String Url);
    InputStream loadAudio(String token, String Url);
}

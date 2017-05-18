package com.example.scindapsus.service.scene;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;

import org.reactivestreams.Subscriber;

import java.io.InputStream;

import javax.inject.Inject;


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
    public void loadAudio(String token, Subscriber<InputStream> subscriber, String Url) {
        fileHttp.getFile(token, subscriber, Url);
    }

    @Override
    public InputStream loadAudio(String token, String Url) {
        return null;
    }

}

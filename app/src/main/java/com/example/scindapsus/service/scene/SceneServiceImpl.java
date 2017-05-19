package com.example.scindapsus.service.scene;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.util.custom.LinesAudioDownloader;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;


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
    public void loadAudio(String token, Observer<InputStream> subscriber, String Url) {
        fileHttp.getFile(token, subscriber, Url);
    }

    @Override
    public InputStream loadAudio(String token, String Url) {
        return null;
    }

    @Override
    public List<Line> loadAudio(String token, List<Line> lines) {
        LinesAudioDownloader linesAudioDownloader = new LinesAudioDownloader(this, token, lines);
        linesAudioDownloader.startDownload();
        return null;
    }

}

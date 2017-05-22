package com.example.scindapsus.vp.participate.scene;

import android.support.annotation.NonNull;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.scene.SceneService;
import com.example.scindapsus.service.shared.SharedService;

import java.io.InputStream;
import java.util.List;


import javax.inject.Inject;

/**
 * Created by ej on 5/4/2017.
 */

public class ScenePresenter implements SceneContract.Presenter {

    final static String TAG = ScenePresenter.class.getName();

    private Scene mScene;
    private final SceneContract.View mSceneView;

    @Inject
    SceneService sceneService;
    @Inject
    SharedService sharedService;

    public ScenePresenter(@NonNull SceneContract.View view, @NonNull ApplicationComponent applicationComponent) {
        mSceneView = view;
        mSceneView.setPresenter(this);

        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    public Scene getScene() {
        return mScene;
    }

    public void setScene(Scene scene) {
        this.mScene = scene;
        mSceneView.showLines(scene.getLines());
        loadLinesAudio(scene.getLines());
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    class InputAndLine {
        Line line;
        InputStream inputStream;
        public InputAndLine(Line line, InputStream inputStream) {
            this.line = line;
            this.inputStream = inputStream;
        }
    }

    private void loadLinesAudio(List<Line> lines) {
        final String token = sharedService.getToken();
        List<Line> local = sceneService.loadAudio(token, lines);
    }
}

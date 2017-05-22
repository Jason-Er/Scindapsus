package com.example.scindapsus.vp.participate.scene;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.scene.SceneService;
import com.example.scindapsus.service.shared.SharedService;

import java.util.List;


import javax.inject.Inject;

/**
 * Created by ej on 5/4/2017.
 */

public class ScenePresenter implements SceneContract.Presenter {

    final static String TAG = ScenePresenter.class.getName();

    private Scene mScene;
    private Context context;
    private final SceneContract.View mSceneView;

    @Inject
    SceneService sceneService;
    @Inject
    SharedService sharedService;

    public ScenePresenter(@NonNull SceneContract.View view, @NonNull ApplicationComponent applicationComponent) {
        mSceneView = view;
        mSceneView.setPresenter(this);

        context = applicationComponent.getApplication().getBaseContext();
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

    private void loadLinesAudio(List<Line> lines) {
        // TODO: 5/22/2017 path4Save need refactoring according to the user login
        String path4Save = context.getFilesDir().getAbsolutePath();
        final String token = sharedService.getToken();
        List<Line> local = sceneService.loadAudio(token, lines, path4Save);
    }
}

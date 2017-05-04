package com.example.scindapsus.vp.participate.scene;

import android.support.annotation.NonNull;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.scene.SceneService;
import com.example.scindapsus.service.shared.SharedService;

import javax.inject.Inject;

/**
 * Created by ej on 5/4/2017.
 */

public class ScenePresenter implements SceneContract.Presenter {

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
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}

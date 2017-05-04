package com.example.scindapsus.vp.participate.scene;

import com.example.scindapsus.model.Scene;

/**
 * Created by ej on 5/4/2017.
 */

public class ScenePresenter implements SceneContract.Presenter {

    private Scene mScene;

    public ScenePresenter(Scene mScene) {
        this.mScene = mScene;
    }

    public Scene getScene() {
        return mScene;
    }

    public void setScene(Scene scene) {
        this.mScene = scene;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}

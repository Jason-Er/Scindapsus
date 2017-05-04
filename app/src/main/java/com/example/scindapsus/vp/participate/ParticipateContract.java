package com.example.scindapsus.vp.participate;

import com.example.scindapsus.global.BasePresenter;
import com.example.scindapsus.global.BaseView;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.vp.participate.scene.ScenePresenter;

/**
 * Created by ej on 3/29/2017.
 */

public interface ParticipateContract {

    interface View extends BaseView<ParticipateContract.Presenter> {
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter {
        void loadPlay(boolean forceUpdate, int id);
        void subscribe(PlayInfo playInfo);
        void setScenePresenter(ScenePresenter scenePresenter);
    }

}


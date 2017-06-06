package com.example.scindapsus.vp.participate.scene;

import com.example.scindapsus.global.BasePresenter;
import com.example.scindapsus.global.BaseView;
import com.example.scindapsus.model.Line;

import java.io.File;
import java.util.List;

/**
 * Created by ej on 5/4/2017.
 */

public class SceneContract {

    interface View extends BaseView<SceneContract.Presenter> {
        void showLines(List<Line> lines);
    }

    interface Presenter extends BasePresenter {
        void uploadToServer();
    }
}

package com.example.scindapsus.mvp.login;

import com.example.scindapsus.global.BasePresenter;
import com.example.scindapsus.global.BaseView;
/**
 * Created by ej on 2/21/2017.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void logIn();

        void logUp();
    }
}

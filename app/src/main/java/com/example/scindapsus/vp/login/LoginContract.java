package com.example.scindapsus.vp.login;

import com.example.scindapsus.global.BasePresenter;
import com.example.scindapsus.global.BaseView;
import com.example.scindapsus.model.User;

/**
 * Created by ej on 2/21/2017.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void navigateToBrowse();

    }

    interface Presenter extends BasePresenter {

        void login(User user);

        void logUp();
    }
}

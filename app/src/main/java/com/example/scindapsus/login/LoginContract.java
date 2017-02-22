package com.example.scindapsus.login;

import com.example.scindapsus.BasePresenter;
import com.example.scindapsus.BaseView;
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

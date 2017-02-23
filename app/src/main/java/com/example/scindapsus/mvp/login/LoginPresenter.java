package com.example.scindapsus.mvp.login;

import android.support.annotation.NonNull;

import com.example.scindapsus.data.source.LoginRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 2/21/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginRepository mLoginRepository;
    private final LoginContract.View mLogInView;

    public LoginPresenter(@NonNull LoginRepository loginRepository,
                               @NonNull LoginContract.View logInView) {
        mLoginRepository = checkNotNull(loginRepository, "loginRepository cannot be null!");
        mLogInView = checkNotNull(logInView, "logInView cannot be null!");

        mLogInView.setPresenter(this);
    }

    @Override
    public void logIn() {

    }

    @Override
    public void logUp() {

    }

    @Override
    public void start() {

    }
}

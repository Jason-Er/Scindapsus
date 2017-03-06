package com.example.scindapsus.vp.login;

import android.support.annotation.NonNull;

import com.example.scindapsus.model.Token;
import com.example.scindapsus.model.User;

import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.login.LoginService;

import javax.inject.Inject;

import rx.Subscriber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 2/21/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLogInView;

    @Inject LoginService loginService;

    public LoginPresenter(@NonNull LoginContract.View logInView) {
        mLogInView = checkNotNull(logInView, "logInView cannot be null!");
        mLogInView.setPresenter(this);
        DaggerServiceComponent.builder().build().inject(this);
    }

    @Override
    public void login(User user) {
        System.out.println(user);

        Subscriber subscriber = new Subscriber<Token>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Token token) {

            }
        };

        loginService.login(subscriber, user.name(), user.password());
        //mLoginRepository.login(subscriber, user.name(), user.password());

        /*
        mLogInView.setLoadingIndicator(true);

        // TO-DO something

        mLogInView.setLoadingIndicator(false);
        */
    }

    @Override
    public void logUp() {

    }

    @Override
    public void start() {

    }
}

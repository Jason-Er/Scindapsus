package com.example.scindapsus.vp.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Auth;
import com.example.scindapsus.model.Token;
import com.example.scindapsus.model.User;

import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.login.LoginService;
import com.example.scindapsus.service.shared.SharedService;

import javax.inject.Inject;

import rx.Subscriber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 2/21/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final String TAG = LoginPresenter.class.getName();
    private final LoginContract.View mLogInView;

    @Inject
    LoginService loginService;
    @Inject
    SharedService sharedService;

    public LoginPresenter(@NonNull LoginContract.View logInView, @NonNull ApplicationComponent applicationComponent) {
        mLogInView = checkNotNull(logInView, "logInView cannot be null!");
        mLogInView.setPresenter(this);
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public void login(final User user) {
        /*
        Subscriber subscriber = new Subscriber<Token>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Token token) {
                Log.i(TAG, "onNext");
                sharedService.saveToken(token.token());
                mLogInView.navigateToBrowse(user);
            }
        };

        loginService.login(subscriber, user.name(), user.password());
        //mLoginRepository.login(subscriber, user.name(), user.password());


        mLogInView.setLoadingIndicator(true);

        // TO-DO something

        mLogInView.setLoadingIndicator(false);
        */
        mLogInView.navigateToBrowse(user);

    }

    @Override
    public void logUp() {

    }

    @Override
    public void start() {

    }
}

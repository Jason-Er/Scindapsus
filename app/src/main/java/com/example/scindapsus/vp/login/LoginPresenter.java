package com.example.scindapsus.vp.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.scindapsus.global.ApplicationComponent;

import com.example.scindapsus.model.Token;
import com.example.scindapsus.model.User;

import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.login.LoginService;
import com.example.scindapsus.service.shared.SharedService;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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

        Observer subscriber = new Observer<Token>() {

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(Token token) {
                Log.i(TAG, "onNext");
                sharedService.saveToken(token.token());
                mLogInView.navigateToBrowse(user);
            }


            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onCompleted");
            }
        };

        loginService.login(subscriber, user.getUsername(), user.getPassword());

        mLogInView.setLoadingIndicator(true);

        // TO-DO something

        mLogInView.setLoadingIndicator(false);

        // mLogInView.navigateToBrowse(user);

    }

    @Override
    public void logUp() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}

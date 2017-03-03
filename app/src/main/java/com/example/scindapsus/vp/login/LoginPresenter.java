package com.example.scindapsus.vp.login;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.scindapsus.data.source.LoginRepository;
import com.example.scindapsus.model.HttpResult;
import com.example.scindapsus.model.Token;
import com.example.scindapsus.model.User;

import rx.Subscriber;

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

        mLoginRepository.login(subscriber, user.name(), user.password());

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

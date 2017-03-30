package com.example.scindapsus.vp.login;

import android.os.Bundle;

import com.example.scindapsus.R;
import com.example.scindapsus.global.BaseActivity;
import com.example.scindapsus.global.ScindapsusApplication;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        // attach fragment to main layout
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.login_frame);

        if (loginFragment == null) {
            loginFragment = new LoginFragment();

            addFragment(R.id.login_frame, loginFragment);
        }

        // Create the presenter
        new LoginPresenter(loginFragment, ((ScindapsusApplication)getApplication()).getAppComponent());

    }
}

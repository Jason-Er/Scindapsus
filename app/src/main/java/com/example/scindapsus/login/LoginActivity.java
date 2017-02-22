package com.example.scindapsus.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.scindapsus.R;
import com.example.scindapsus.data.source.LoginRepository;
import com.example.scindapsus.util.ActivityUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (loginFragment == null) {
            loginFragment = new LoginFragment();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    loginFragment, R.id.contentFrame);
        }

        // Create the presenter
        new LoginPresenter( LoginRepository.getInstance(),
                loginFragment);

    }
}

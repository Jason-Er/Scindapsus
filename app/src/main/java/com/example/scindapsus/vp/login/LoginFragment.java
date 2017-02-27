package com.example.scindapsus.vp.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.scindapsus.R;
import com.example.scindapsus.model.User;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 2/21/2017.
 */

public class LoginFragment extends Fragment implements LoginContract.View{

    private LoginContract.Presenter mPresenter;
    private EditText mUserName;
    private EditText mPassword;
    private Button mLogIn;
    private Button mLogUp;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.login_frag, container, false);

        mUserName = (EditText) root.findViewById(R.id.login_userName);
        mPassword = (EditText) root.findViewById(R.id.login_password);

        // Set up button
        mLogIn = (Button) root.findViewById(R.id.login_logIn);
        mLogUp = (Button) root.findViewById(R.id.login_logUp);

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.logIn(User.newInstance(0, mUserName.getText().toString(), mPassword.getText().toString()));
            }
        });

        mLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.logUp();
            }
        });

        return root;
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            // show Hourglass
        }
    }
}

package com.example.scindapsus.vp.participate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.scindapsus.R;
import com.example.scindapsus.global.BaseActivity;
import com.example.scindapsus.global.ScindapsusApplication;

public class ParticipateActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ParticipateActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participate_act);

        // attach fragment to main layout
        ParticipateFragment participateFragment = (ParticipateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.participate_frame);

        if (participateFragment == null) {
            participateFragment = new ParticipateFragment();

            addFragment(R.id.participate_frame, participateFragment);
        }

        // Create the presenter
        new ParticipatePresenter(participateFragment, ((ScindapsusApplication)getApplication()).getAppComponent());
    }
}

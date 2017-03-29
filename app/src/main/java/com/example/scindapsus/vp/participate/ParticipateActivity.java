package com.example.scindapsus.vp.participate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.scindapsus.R;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.util.ActivityUtils;

public class ParticipateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participate_act);

        // attach fragment to main layout
        ParticipateFragment participateFragment = (ParticipateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.participate_frame);

        if (participateFragment == null) {
            participateFragment = new ParticipateFragment();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    participateFragment, R.id.participate_frame);
        }

        // Create the presenter
        new ParticipatePresenter(participateFragment, ((ScindapsusApplication)getApplication()).getAppComponent());
    }
}

package com.example.scindapsus.vp.participate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;

import com.example.scindapsus.R;
import com.example.scindapsus.global.BaseActivity;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.global.navigation.Navigator;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.User;
import com.example.scindapsus.util.bus.RxBus;

public class ParticipateActivity extends BaseActivity {

    private static String TAG = ParticipateActivity.class.getName();

    public static Intent getCallingIntent(@NonNull Context context) {
        return new Intent(context, ParticipateActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participate_act);
        setupWindowAnimations();
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

    private void setupWindowAnimations() {
        Slide slideToBottom = new Slide(Gravity.BOTTOM);
        slideToBottom.setDuration(1000);
        getWindow().setEnterTransition(slideToBottom);
    }
}

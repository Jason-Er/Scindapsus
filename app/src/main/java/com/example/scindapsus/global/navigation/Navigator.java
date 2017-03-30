package com.example.scindapsus.global.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.scindapsus.vp.browse.BrowseActivity;
import com.example.scindapsus.vp.participate.ParticipateActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 3/29/2017.
 */
@Singleton
public class Navigator {
    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToBrowse(Context context) {
        if (context != null) {
            Intent intentToLaunch = BrowseActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToParticipate(Context context) {
        if (context != null) {
            Intent intentToLaunch = ParticipateActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}

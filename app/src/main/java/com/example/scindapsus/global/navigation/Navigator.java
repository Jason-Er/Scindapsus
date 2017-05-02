package com.example.scindapsus.global.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.scindapsus.vp.browse.BrowseActivity;
import com.example.scindapsus.vp.participate.ParticipateActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 3/29/2017.
 */
@Singleton
public class Navigator {
    public static final String PARA_MACRO = "PARCELABLE";
    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToBrowse(@NonNull Context context, Parcelable parcelable) {

        Intent intentToLaunch = BrowseActivity.getCallingIntent(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARA_MACRO, parcelable);
        intentToLaunch.putExtras(bundle);
        context.startActivity(intentToLaunch);

    }

    public void navigateToParticipate(@NonNull Context context, Parcelable parcelable) {

        Intent intentToLaunch = ParticipateActivity.getCallingIntent(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARA_MACRO, parcelable);
        intentToLaunch.putExtras(bundle);
        context.startActivity(intentToLaunch);

    }
}

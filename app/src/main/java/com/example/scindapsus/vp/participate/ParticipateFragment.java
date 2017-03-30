package com.example.scindapsus.vp.participate;

import android.support.v4.app.Fragment;

import com.example.scindapsus.global.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by ej on 3/29/2017.
 */

public class ParticipateFragment extends Fragment implements ParticipateContract.View{
    @Inject
    Navigator navigator;

    @Override
    public void setPresenter(ParticipateContract.Presenter presenter) {

    }
}

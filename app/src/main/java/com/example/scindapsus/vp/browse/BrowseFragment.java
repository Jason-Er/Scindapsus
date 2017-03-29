package com.example.scindapsus.vp.browse;

import android.support.v4.app.Fragment;

import com.example.scindapsus.global.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by ej on 3/29/2017.
 */

public class BrowseFragment extends Fragment implements BrowseContract.View{
    @Inject
    Navigator navigator;

    @Override
    public void setPresenter(BrowseContract.Presenter presenter) {

    }
}

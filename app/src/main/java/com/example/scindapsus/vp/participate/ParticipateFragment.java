package com.example.scindapsus.vp.participate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scindapsus.R;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.global.navigation.Navigator;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.vp.participate.scene.SceneFragment;
import com.example.scindapsus.vp.participate.scene.ScenePresenter;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class ParticipateFragment extends Fragment implements ParticipateContract.View{

    private static final String TAG = ParticipateFragment.class.getName();

    private ParticipateContract.Presenter mPresenter;
    private PlayInfo playInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.participate_frag, container, false);


        // attach fragment to main layout
        SceneFragment sceneFragment = (SceneFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.participate_frag_frame);

        if(sceneFragment == null) {
            sceneFragment = new SceneFragment();
            FragmentManager manager = getChildFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.participate_frag_frame, sceneFragment);
            transaction.commit();
        }

        // Create the presenter
        ScenePresenter scenePresenter = new ScenePresenter(sceneFragment, ((ScindapsusApplication)getActivity().getApplication()).getAppComponent());
        mPresenter.setScenePresenter(scenePresenter);
        playInfo = (PlayInfo) getActivity().getIntent().getParcelableExtra(Navigator.PARA_MACRO);
        return root;
    }

    @Override
    public void setPresenter(@NonNull ParticipateContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(playInfo);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }
}

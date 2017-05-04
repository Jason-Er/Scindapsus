package com.example.scindapsus.vp.participate.scene;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scindapsus.R;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.util.custom.sceneComponent.SceneRVAdapter;

/**
 * Created by ej on 5/4/2017.
 */

public class SceneFragment extends Fragment implements SceneContract.View {

    private RecyclerView mRecyclerView;
    private SceneContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.scene_frag, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.scene_frag_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        SceneRVAdapter mAdapter = new SceneRVAdapter(root.getContext(),  ((ScindapsusApplication)getActivity().getApplication()).getAppComponent(), new Scene());
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void setPresenter(@NonNull SceneContract.Presenter presenter) {
        mPresenter = presenter;
    }

}

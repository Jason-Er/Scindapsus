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
import android.widget.Button;

import com.example.scindapsus.R;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.util.custom.browseComponent.BrowseRVAdapter;
import com.example.scindapsus.util.custom.sceneComponent.SceneRVAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ej on 5/4/2017.
 */

public class SceneFragment extends Fragment implements SceneContract.View {

    private SceneContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private Button mButtonSave;
    private SceneRVAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.scene_frag, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.scene_frag_recycler);
        mButtonSave = (Button) root.findViewById(R.id.scene_frag_save);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = new SceneRVAdapter(root.getContext(),  ((ScindapsusApplication)getActivity().getApplication()).getAppComponent(), new ArrayList<Line>());
        mRecyclerView.setAdapter(mAdapter);

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.uploadToServer();
            }
        });

        return root;
    }

    @Override
    public void setPresenter(@NonNull SceneContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLines(List<Line> lines) {
        mAdapter.setDataset(lines);
    }

}

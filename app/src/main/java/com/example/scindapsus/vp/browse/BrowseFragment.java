package com.example.scindapsus.vp.browse;

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
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.PlayInfoEx;
import com.example.scindapsus.util.custom.browseComponent.BrowseRVAdapter;
import com.example.scindapsus.util.custom.browseComponent.BrowseRVLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class BrowseFragment extends Fragment implements BrowseContract.View{

    private BrowseContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private BrowseRVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.browse_frag, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.browse_frag_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(layoutManager);
        /*
        // use a linear layout manager
        mLayoutManager = new BrowseRVLayoutManager();
        mRecyclerView.setLayoutManager(mLayoutManager);
        */
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        // specify an adapter (see also next example)
        mAdapter = new BrowseRVAdapter(new ArrayList<PlayInfoEx>());
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void setPresenter(@NonNull BrowseContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showPlaysInfo(List<PlayInfoEx> playsInfo) {
        mAdapter.setDataset(playsInfo);
    }
}

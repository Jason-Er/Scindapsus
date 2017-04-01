package com.example.scindapsus.vp.browse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scindapsus.R;
import com.example.scindapsus.util.custom.browseComponent.BrowseRVAdapter;

/**
 * Created by ej on 3/29/2017.
 */

public class BrowseFragment extends Fragment implements BrowseContract.View{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    String[] mDataset = {"1","2"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.browse_frag, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.browse_frag_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BrowseRVAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void setPresenter(BrowseContract.Presenter presenter) {

    }
}

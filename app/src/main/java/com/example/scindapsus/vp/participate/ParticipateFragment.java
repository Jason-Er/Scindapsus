package com.example.scindapsus.vp.participate;

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
import com.example.scindapsus.global.navigation.Navigator;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.util.custom.participateComponent.ParticipateRVAdapter;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class ParticipateFragment extends Fragment implements ParticipateContract.View{

    private static final String TAG = ParticipateFragment.class.getName();

    private RecyclerView mRecyclerView;
    private ParticipateContract.Presenter mPresenter;
    private ParticipateRVAdapter mAdapter;
    private PlayInfo playInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.participate_frag, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.participate_frag_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = new ParticipateRVAdapter(root.getContext(),  ((ScindapsusApplication)getActivity().getApplication()).getAppComponent(), new ArrayList<Play>());
        mRecyclerView.setAdapter(mAdapter);

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

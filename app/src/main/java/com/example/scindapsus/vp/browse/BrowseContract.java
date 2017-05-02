package com.example.scindapsus.vp.browse;

import android.os.Parcelable;

import com.example.scindapsus.global.BasePresenter;
import com.example.scindapsus.global.BaseView;
import com.example.scindapsus.model.PlayInfo;

import java.util.List;

/**
 * Created by ej on 3/29/2017.
 */

public interface BrowseContract {

    interface View extends BaseView<BrowseContract.Presenter> {

        void setLoadingIndicator(boolean active);
        void showPlaysInfo(List<PlayInfo> playsInfo);
        void navigateToParticipate(Parcelable parcelable);

    }

    interface Presenter extends BasePresenter {
        void onDestroy();
        void loadPlaysInfo(boolean forceUpdate);
        void recyclerViewItemClick(android.view.View view, int position);
    }
}

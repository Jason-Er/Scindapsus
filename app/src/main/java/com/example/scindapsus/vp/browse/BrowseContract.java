package com.example.scindapsus.vp.browse;

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

    }

    interface Presenter extends BasePresenter {
        void loadPlaysInfo(boolean forceUpdate);
    }
}

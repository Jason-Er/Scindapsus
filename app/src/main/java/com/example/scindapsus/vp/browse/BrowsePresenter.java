package com.example.scindapsus.vp.browse;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.browse.BrowseService;
import com.example.scindapsus.service.image.ImageService;
import com.example.scindapsus.service.shared.SharedService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ej on 3/29/2017.
 */

public class BrowsePresenter implements BrowseContract.Presenter{

    private final String TAG = BrowsePresenter.class.getName();

    private final BrowseContract.View mBrowseView;
    private boolean mFirstLoad = true;

    private List<PlayInfo> playsInfo = null;

    @Inject
    BrowseService browseService;
    @Inject
    ImageService imageService;
    @Inject
    SharedService sharedService;

    public BrowsePresenter(@NonNull BrowseContract.View browseView, @NonNull ApplicationComponent applicationComponent) {
        mBrowseView = checkNotNull(browseView, "browseView cannot be null!");
        mBrowseView.setPresenter(this);
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public void subscribe() {
        loadPlaysInfo(false);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }

    @Override
    public void loadPlaysInfo(boolean forceUpdate) {
        loadPlaysInfo(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    @Override
    public void recyclerViewItemClick(View view, int position) {
        Log.i(TAG, "recyclerViewItemClick position: "+position);
        mBrowseView.navigateToParticipate(playsInfo.get(position));
    }

    private void loadPlaysInfo(final boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mBrowseView.setLoadingIndicator(true);
        }

        Observer observer = new Observer<PageResult<List<PlayInfo>>>() {
            @Override
            public void onComplete() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");

            }

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(PageResult<List<PlayInfo>> pageResult) {
                Log.i(TAG, "onNext");
                processPlaysInfo(pageResult);
            }

        };

        browseService.loadPlaysInfo(sharedService.getToken(), 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void processPlaysInfo(@NonNull PageResult<List<PlayInfo>> pageResult) {
        playsInfo = pageResult.getContent();
        if (playsInfo.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            processEmptyPlaysInfo();
        } else {
            mBrowseView.showPlaysInfo(playsInfo);
        }
    }

    private void processEmptyPlaysInfo() {

    }
}

package com.example.scindapsus.util.custom.browseComponent;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by ej on 4/1/2017.
 */

public class BrowseRVLayoutManager extends RecyclerView.LayoutManager{

    final static String TAG = BrowseRVLayoutManager.class.getName();

    private int mTotalHeight;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //如果没有item，直接返回
        if (getItemCount() <= 0) return;
        // 跳过preLayout，preLayout主要用于支持动画
        if (state.isPreLayout()) {
            return;
        }
        //在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler);
        //定义竖直方向的偏移量
        int offsetY = 0;
        int totalHeight = 0;
        for (int i = 0; i < getItemCount(); i++) {

            //这里就是从缓存里面取出
            View view = recycler.getViewForPosition(i);
            //将View加入到RecyclerView中
            addView(view);

            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            //最后，将View布局
            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            //将竖直方向偏移量增大height
            offsetY += height;
            //
            totalHeight += height;
            if(totalHeight > getVerticalSpace()) break;
        }
        Log.i(TAG, "ChildCount: " + this.getChildCount());
        //如果所有子View的高度和没有填满RecyclerView的高度，
        // 则将高度设置为RecyclerView的高度
        mTotalHeight = Math.max(totalHeight, getVerticalSpace());
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        //Take top measurements from the top-left child
        final View topView = getChildAt(0);
        //Take bottom measurements from the bottom-right child.
        final View bottomView = getChildAt(getChildCount()-1);

        //Optimize the case where the entire data set is too small to scroll
        int viewSpan = getDecoratedBottom(bottomView) - getDecoratedTop(topView);
        if (viewSpan <= getVerticalSpace()) {
            //We cannot scroll in either direction
            return 0;
        }

        int delta = 0;
        boolean topBoundReached = false;
        boolean bottomBoundReached = false;

        if (dy > 0) { // Contents are scrolling up
            //Check against bottom bound
            if (bottomBoundReached) {

            } else {
                //No limits while the last row isn't visible
                delta = -dy;
            }
        } else { // Contents are scrolling down
            //Check against top bound
            if (topBoundReached) {
                int topOffset = -getDecoratedTop(topView) + getPaddingTop();
                delta = Math.min(-dy, topOffset);
            } else {
                delta = -dy;
            }
        }

        offsetChildrenVertical(delta);

        Log.i(TAG, "delta: " + delta);

        return delta;
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }
}

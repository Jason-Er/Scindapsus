package com.example.scindapsus.util.custom.browseComponent;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ej on 4/1/2017.
 */

public class BrowseRVLayoutManager extends RecyclerView.LayoutManager{

    private int mDecoratedChildWidth;
    private int mDecoratedChildHeight;
    private int mFirstVisiblePosition;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //Scrap measure one child
        View scrap = recycler.getViewForPosition(0);
        addView(scrap);
        measureChildWithMargins(scrap, 0, 0);

        mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap);
        mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap);
        detachAndScrapView(scrap, recycler);

        // updateWindowSizing();

        int childTop;
        /*
         * Reset the visible and scroll positions
         */
        mFirstVisiblePosition = 0;
        childTop = 0;

        //Clear all attached views into the recycle bin
        detachAndScrapAttachedViews(recycler);
        //Fill the grid for the initial layout of views
        // fillViews(childTop, recycler);
    }
}

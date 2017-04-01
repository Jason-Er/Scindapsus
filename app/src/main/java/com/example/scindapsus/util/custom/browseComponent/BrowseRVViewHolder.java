package com.example.scindapsus.util.custom.browseComponent;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by ej on 4/1/2017.
 */

public class BrowseRVViewHolder extends RecyclerView.ViewHolder{
    // each data item is just a string in this case
    public TextView mTextView;

    public BrowseRVViewHolder(TextView v) {
        super(v);
        mTextView = v;
    }

    public void setText(String text) {
        mTextView.setText(text);
    }
}

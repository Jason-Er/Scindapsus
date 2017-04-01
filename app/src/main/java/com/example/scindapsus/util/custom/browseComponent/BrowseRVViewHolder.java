package com.example.scindapsus.util.custom.browseComponent;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.scindapsus.R;

/**
 * Created by ej on 4/1/2017.
 */

public class BrowseRVViewHolder extends RecyclerView.ViewHolder{
    // each data item is just a string in this case
    public TextView mTextView;

    public BrowseRVViewHolder(View v) {
        super(v);
        mTextView =(TextView) v.findViewById(R.id.info_text);
    }

    public void populate(String text) {
        mTextView.setText(text);
    }
}

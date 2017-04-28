package com.example.scindapsus.util.custom.browseComponent;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scindapsus.R;
import com.example.scindapsus.model.PlayInfoEx;

import java.util.List;

/**
 * Created by ej on 3/31/2017.
 */

public class BrowseRVAdapter extends RecyclerView.Adapter<BrowseRVAdapter.ViewHolder> {

    private final static String TAG = BrowseRVAdapter.class.getName();

    private List<PlayInfoEx> dataset;

    public BrowseRVAdapter(List<PlayInfoEx> myDataset) {
        dataset = myDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView extractView;
        private TextView nameView;
        private ImageView stillView;
        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            extractView = (TextView)v.findViewById(R.id.card_view_extract);
            nameView = (TextView)v.findViewById(R.id.card_view_name);
            stillView = (ImageView)v.findViewById(R.id.card_view_still);
        }

        public void populate(PlayInfoEx s) {
            nameView.setText(s.getName());
            extractView.setText(s.getExtract());
            // stillView.setImageBitmap(s.getStill());
        }
    }

    public void setDataset(@NonNull List<PlayInfoEx> dataset) {
        Log.i(TAG, "setDataset");
        this.dataset = dataset;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BrowseRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.populate(dataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount size: "+dataset.size());
        return dataset.size();
    }

}

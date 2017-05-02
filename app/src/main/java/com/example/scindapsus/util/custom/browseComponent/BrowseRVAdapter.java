package com.example.scindapsus.util.custom.browseComponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scindapsus.R;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.shared.SharedService;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ej on 3/31/2017.
 */

public class BrowseRVAdapter extends RecyclerView.Adapter<BrowseRVAdapter.ViewHolder> {

    private final static String TAG = BrowseRVAdapter.class.getName();

    private List<PlayInfo> dataset;
    private Context context;

    @Inject
    SharedService sharedService;

    public BrowseRVAdapter(@NonNull Context context, @NonNull ApplicationComponent applicationComponent, List<PlayInfo> dataset) {
        this.context = context;
        this.dataset = dataset;
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
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

        public void populate(PlayInfo s) {
            nameView.setText(s.getName());
            extractView.setText(s.getExtract());
        }
    }

    public void setDataset(@NonNull List<PlayInfo> dataset) {
        Log.i(TAG, "setDataset");
        this.dataset = dataset;
        notifyDataSetChanged();
    }

    @Override
    public BrowseRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(dataset.get(position));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", sharedService.getToken())
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();

        picasso.load(dataset.get(position).getStillUrl()).into(holder.stillView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount size: "+dataset.size());
        return dataset.size();
    }

}

package com.example.scindapsus.util.custom.sceneComponent;

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
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.service.DaggerServiceComponent;
import com.example.scindapsus.service.shared.SharedService;
import com.example.scindapsus.util.bus.RxBus;
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

public class SceneRVAdapter extends RecyclerView.Adapter<SceneRVAdapter.ViewHolder> {

    private final static String TAG = SceneRVAdapter.class.getName();

    private List<Line> lines;
    private Context context;

    @Inject
    SharedService sharedService;

    public SceneRVAdapter(@NonNull Context context, @NonNull ApplicationComponent applicationComponent, List<Line> lines) {
        this.context = context;
        this.lines = lines;
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lineView;
        private ImageView roleView;

        public ViewHolder(View v) {
            super(v);
            lineView = (TextView)v.findViewById(R.id.line_cv_text);
            roleView = (ImageView)v.findViewById(R.id.line_cv_role);
        }

        public void populate(Line line) {
            lineView.setText(line.getText());
            //extractView.setText(s.getExtract());
        }
    }

    public void setDataset(@NonNull List<Line> lines) {
        Log.i(TAG, "setDataset");
        this.lines = lines;
        notifyDataSetChanged();
    }

    @Override
    public SceneRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_card_view, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.populate(lines.get(position));
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

}

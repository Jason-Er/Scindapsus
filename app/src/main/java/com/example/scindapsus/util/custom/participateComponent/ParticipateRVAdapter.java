package com.example.scindapsus.util.custom.participateComponent;

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
import com.example.scindapsus.model.Play;
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

public class ParticipateRVAdapter extends RecyclerView.Adapter<ParticipateRVAdapter.ViewHolder> implements View.OnClickListener {

    private final static String TAG = ParticipateRVAdapter.class.getName();

    private List<Play> dataset;
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;

    @Inject
    SharedService sharedService;

    public ParticipateRVAdapter(@NonNull Context context, @NonNull ApplicationComponent applicationComponent, List<Play> dataset) {
        this.context = context;
        this.dataset = dataset;
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int)view.getTag());
            RxBus.getDefault().post(dataset.get((int)view.getTag()));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView extractView;
        private TextView nameView;
        private ImageView stillView;

        public ViewHolder(View v) {
            super(v);
            extractView = (TextView)v.findViewById(R.id.card_view_extract);
            nameView = (TextView)v.findViewById(R.id.card_view_name);
            stillView = (ImageView)v.findViewById(R.id.card_view_still);
        }

        public void populate(Play s) {
            //nameView.setText(s.getName());
            //extractView.setText(s.getExtract());
        }
    }

    public void setDataset(@NonNull List<Play> dataset) {
        Log.i(TAG, "setDataset");
        this.dataset = dataset;
        notifyDataSetChanged();
    }

    @Override
    public ParticipateRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playinfo_card_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
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


    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount size: "+dataset.size());
        return dataset.size();
    }

}

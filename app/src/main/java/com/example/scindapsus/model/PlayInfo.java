package com.example.scindapsus.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ej on 4/25/2017.
 */
@AutoValue
public abstract class PlayInfo implements Parcelable {
    public abstract long id();
    public abstract String name();
    public abstract String stillUrl();
    public abstract String extract();

    public static PlayInfo create(long id, String name, String stillUrl, String extract) {
        return new AutoValue_PlayInfo(id, name, stillUrl, extract);
    }
    public static TypeAdapter<PlayInfo> typeAdapter(Gson gson) {
        return new AutoValue_PlayInfo.GsonTypeAdapter(gson);
    }

}

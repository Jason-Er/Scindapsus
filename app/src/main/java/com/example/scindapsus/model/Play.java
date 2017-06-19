package com.example.scindapsus.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by ej on 5/3/2017.
 */

@AutoValue
public abstract class Play implements PlayModel {
    @Nullable
    public abstract List<Scene> scenes();
    @Nullable
    public abstract List<Role> cast();
    public static Play create(long id, String name, String extract, String still_url, List<Scene> scenes, List<Role> cast) {
        return new AutoValue_Play(id, name, extract, still_url, scenes, cast);
    }
    public static TypeAdapter<Play> typeAdapter(Gson gson) {
        return new AutoValue_Play.GsonTypeAdapter(gson);
    }
    public static final Factory<Play> FACTORY = new Factory<>(new Creator<Play>() {
        @Override
        public Play create(long id, @NonNull String name, @Nullable String extract, @Nullable String still_url) {
            return new AutoValue_Play(id, name, extract, still_url, null, null);
        }
    });
}

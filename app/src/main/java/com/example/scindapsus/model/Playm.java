package com.example.scindapsus.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by ej on 5/25/2017.
 */
@AutoValue
public abstract class PlayM implements PlayModel{
    public static final Factory<PlayM> FACTORY = new Factory<>(new Creator<PlayM>() {
        @Override
        public PlayM create(@Nullable String still_url, @Nullable String extract, long id, @NonNull String name) {
            return new AutoValue_PlayM(still_url, extract, id, name);
        }
    });
}

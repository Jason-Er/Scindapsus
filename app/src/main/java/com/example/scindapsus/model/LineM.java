package com.example.scindapsus.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by ej on 5/27/2017.
 */
@AutoValue
public abstract class LineM implements LineModel{
    public static final Factory<LineM> FACTORY = new LineModel.Factory<>(new Creator<LineM>() {
        @Override
        public LineM create(@Nullable Long ordinal, @Nullable String audiourl, long scene_id, long id, @NonNull String text) {
            return new AutoValue_LineM(ordinal, audiourl, scene_id, id, text);
        }
    });
}

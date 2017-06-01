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
        public LineM create(long id, @NonNull String text, @Nullable String audiourl, @Nullable String audiourl_local, @Nullable Long ordinal, long scene_id) {
            return new AutoValue_LineM(id, text, audiourl, audiourl_local, ordinal, scene_id);
        }
    });
}

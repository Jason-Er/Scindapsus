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
        public LineM create(long id, long ordinal, @NonNull String text, @Nullable String audiourl, long scene_id) {
            return new AutoValue_LineM(id, ordinal, text, audiourl, scene_id);
        }
    });

    public static LineM create(long id, Long ordinal, @NonNull String text, @Nullable String audiourl, long scene_id) {
        return new AutoValue_LineM(id, ordinal, text, audiourl, scene_id);
    }
}

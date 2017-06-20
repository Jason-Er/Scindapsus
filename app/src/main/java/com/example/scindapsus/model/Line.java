package com.example.scindapsus.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ej on 5/3/2017.
 */
@AutoValue
public abstract class Line implements LineModel{
    @Nullable
    public abstract Voice voice();
    public static Line create(long id, long ordinal, String text, String audio_url, long role_id, long scene_id, Voice voice) {
        return new AutoValue_Line(id, ordinal, text, audio_url, role_id, scene_id, voice);
    }
    public static TypeAdapter<Line> typeAdapter(Gson gson) {
        return new AutoValue_Line.GsonTypeAdapter(gson);
    }
    public static final Factory<Line> FACTORY = new Factory<>(new Creator<Line>() {
        @Override
        public Line create(long id, long ordinal, @NonNull String text, @Nullable String audio_url, long role_id, long scene_id) {
            return new AutoValue_Line(id, ordinal, text, audio_url, role_id, scene_id, null);
        }
    });
}

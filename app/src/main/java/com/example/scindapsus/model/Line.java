package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ej on 5/3/2017.
 */
@AutoValue
public abstract class Line implements LineModel{
    public static Line create(long id, long ordinal, String text, String audio_url, long role_id, long scene_id) {
        return new AutoValue_Line(id, ordinal, text, audio_url, role_id, scene_id);
    }
    public static TypeAdapter<Line> typeAdapter(Gson gson) {
        return new AutoValue_Line.GsonTypeAdapter(gson);
    }
}

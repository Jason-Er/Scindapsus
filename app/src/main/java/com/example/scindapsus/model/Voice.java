package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ej on 6/19/2017.
 */
@AutoValue
public abstract class Voice implements VoiceModel{
    public static Voice create(long id, String audiourl_local, long user_id, long line_id) {
        return new AutoValue_Voice(id, audiourl_local, user_id, line_id);
    }
    public static TypeAdapter<Role> typeAdapter(Gson gson) {
        return new AutoValue_Role.GsonTypeAdapter(gson);
    }
    public static final Factory<Voice> FACTORY = new Factory<>(new Creator<Voice>() {
        @Override
        public Voice create(long id, String audiourl_local, long user_id, long line_id) {
            return new AutoValue_Voice(id, audiourl_local, user_id, line_id);
        }
    });
}

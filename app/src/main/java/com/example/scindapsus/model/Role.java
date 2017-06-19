package com.example.scindapsus.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ej on 6/16/2017.
 */
@AutoValue
public abstract class Role implements RoleModel{
    public static Role create(long id, String name, Long age, String gender, String description, Long user_id, Long play_id) {
        return new AutoValue_Role(id, name, age, gender, description, user_id, play_id);
    }
    public static TypeAdapter<Role> typeAdapter(Gson gson) {
        return new AutoValue_Role.GsonTypeAdapter(gson);
    }
    public static final Factory<Role> FACTORY = new Factory<>(new Creator<Role>() {
        @Override
        public Role create(long id, @NonNull String name, @Nullable Long age, @Nullable String gender, @Nullable String description, @Nullable Long user_id, @Nullable Long play_id) {
            return new AutoValue_Role(id, name, age, gender, description, user_id, play_id);
        }
    });
}

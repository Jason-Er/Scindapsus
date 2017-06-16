package com.example.scindapsus.model;

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
}

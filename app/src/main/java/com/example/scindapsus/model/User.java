package com.example.scindapsus.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ej on 3/13/2017.
 */
@AutoValue
public abstract class User implements Parcelable {
    @Nullable
    public abstract Long id();
    public abstract String username();
    @Nullable
    public abstract String password();
    public static User create(Long id, String username, String password) {
        return new AutoValue_User(id, username, password);
    }
    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}

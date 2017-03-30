package com.example.scindapsus.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ej on 3/13/2017.
 */
@AutoValue
public abstract class Auth implements Parcelable {
    @SerializedName("username")
    public abstract String username();

    @SerializedName("password")
    public abstract String password();

    public static Auth newInstance(String username, String password) {
        return new AutoValue_Auth(username, password);
    }
    public static TypeAdapter<Auth> typeAdapter(Gson gson) {
        return new AutoValue_Auth.GsonTypeAdapter(gson);
    }
}

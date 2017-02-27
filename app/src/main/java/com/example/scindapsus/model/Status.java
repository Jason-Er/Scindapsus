package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ej on 2/27/2017.
 */
@AutoValue
public abstract class Status {

    @SerializedName("code")
    public abstract int code();

    @SerializedName("message")
    public abstract String message();

    public static Status newInstance(int code, String message) {
        return new AutoValue_Status(code, message);
    }
    public static TypeAdapter<Status> typeAdapter(Gson gson) {
        return new AutoValue_Status.GsonTypeAdapter(gson);
    }
}

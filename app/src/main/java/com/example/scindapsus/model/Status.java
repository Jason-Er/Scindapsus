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

    @SerializedName("statusCode")
    public abstract int statusCode();

    @SerializedName("messages")
    public abstract String messages();

    public static Status newInstance(int statusCode, String messages) {
        return new AutoValue_Status(statusCode, messages);
    }
    public static TypeAdapter<Status> typeAdapter(Gson gson) {
        return new AutoValue_Status.GsonTypeAdapter(gson);
    }
}

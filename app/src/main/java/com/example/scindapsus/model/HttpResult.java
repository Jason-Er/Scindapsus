package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ej on 2/28/2017.
 */
@AutoValue
public abstract class HttpResult {

    @SerializedName("code")
    public abstract int code();

    @SerializedName("message")
    public abstract String message();

    @SerializedName("data")
    public abstract String data();

    public static TypeAdapter<HttpResult> typeAdapter(Gson gson) {
        return new AutoValue_HttpResult.GsonTypeAdapter(gson);
    }

}

package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ej on 2/28/2017.
 */

@AutoValue
public abstract class Token {
    @SerializedName("token")
    public abstract String token();

    public static Token newInstance(String token) {
        return new AutoValue_Token(token);
    }
    public static TypeAdapter<Token> typeAdapter(Gson gson) {
        return new AutoValue_Token.GsonTypeAdapter(gson);
    }
}

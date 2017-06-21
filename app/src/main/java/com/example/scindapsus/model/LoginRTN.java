package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ej on 2/28/2017.
 */

@AutoValue
public abstract class LoginRTN {

    public abstract String token();
    public abstract User user();

    public static LoginRTN create(String token, User user) {
        return new AutoValue_LoginRTN(token,user);
    }
    public static TypeAdapter<LoginRTN> typeAdapter(Gson gson) {
        return new AutoValue_LoginRTN.GsonTypeAdapter(gson);
    }
}

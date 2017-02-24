package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


/**
 * Created by ej on 2/22/2017.
 */
@AutoValue
public abstract class User implements UserModel {
    public static User newInstance(int _id, String name, String password) {
        return new AutoValue_User(_id, name, password);
    }
    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}

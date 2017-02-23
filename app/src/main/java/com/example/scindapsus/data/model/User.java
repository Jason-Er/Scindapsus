package com.example.scindapsus.data.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

/**
 * Created by ej on 2/22/2017.
 */
@AutoValue
public abstract class User implements UserModel{
    public static final Factory<User> FACTORY = new Factory<>(new Creator<User>() {
        @Override
        public User create(long _id, @NonNull String name, @NonNull String password) {
            return null;
        }
    });
    public static final RowMapper<User> SELECT_ALL_MAPPER = FACTORY.select_allMapper();
}

package com.example.scindapsus.model;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;


import rx.functions.Func1;

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
    public static final Func1<Cursor, User> RX_SELECT_ALL_MAPPER = new Func1<Cursor, User>() {
        @Override
        public User call(Cursor cursor) {
            return SELECT_ALL_MAPPER.map(cursor);
        }
    };
}

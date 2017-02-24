package com.example.scindapsus.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;


import rx.functions.Func1;

/**
 * Created by ej on 2/22/2017.
 */
@AutoValue
public abstract class User implements UserModel {
    public static final Factory<User> FACTORY = new Factory<>(new Creator<User>() {
        @Override
        public User create(long _id, @NonNull String name, @NonNull String password) {
            return new AutoValue_User(_id, name, password);
        }
    });
    public static final RowMapper<User> SELECT_ALL_MAPPER = FACTORY.select_allMapper();

    public static final void Insert_user(@NonNull SQLiteDatabase db, @NonNull String name, @NonNull String password) {
        UserModel.Insert_user insert_user = new Insert_user(db);
        insert_user.bind(name, password);

        new UserModel.Insert_user(db).bind(name,password);

    }

}

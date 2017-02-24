package com.example.scindapsus.service.local;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.scindapsus.model.User;
import com.example.scindapsus.model.UserModel;

/**
 * Created by ej on 2/24/2017.
 */

public abstract class UserService implements UserModel {

    private static SQLiteDatabase db;

    public static final long Insert_user(User user) {
        Insert_user insert = new Insert_user(db);
        insert.bind(user.name(), user.password());
        return insert.program.executeInsert();
    }
}

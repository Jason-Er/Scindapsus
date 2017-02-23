package com.example.scindapsus.data.populator;

import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.model.User;
import com.example.scindapsus.model.UserModel;

/**
 * Created by ej on 2/23/2017.
 */

public class UserPopulator {
    public static void populate(SQLiteDatabase db) {
        db.insert(UserModel.TABLE_NAME, null, User.FACTORY.marshal()
                .name("J. K. Rowling")
                .password("123456")
                .asContentValues());
    }
}

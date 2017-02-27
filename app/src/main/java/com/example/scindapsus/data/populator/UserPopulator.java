package com.example.scindapsus.data.populator;

import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.model.User;
import com.example.scindapsus.model.UserModel;
import com.example.scindapsus.service.local.UserService;

/**
 * Created by ej on 2/23/2017.
 */

public class UserPopulator {
    public static void populate(SQLiteDatabase db) {
        User user = User.newInstance(0, "jason", "123456");
        UserService.Insert_user(user);
    }
}

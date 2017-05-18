package com.example.scindapsus.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.scindapsus.data.populator.UserPopulator;

/**
 * Created by ej on 2/22/2017.
 */

public class LocalDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "scindapsus.db";
    public static final int DB_VERSION = 1;

    private static LocalDbHelper instance;

    public static LocalDbHelper getInstance(Context context) {
        if (null == instance) {
            instance = new LocalDbHelper(context);
        }
        return instance;
    }

    private LocalDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL(UserModel.CREATE_TABLE);
        populate(db);
    }

    private void populate(SQLiteDatabase db) {
        UserPopulator.populate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

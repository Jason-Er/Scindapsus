package com.example.scindapsus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.data.source.local.DatabaseManager;
import com.example.scindapsus.data.source.local.LocalDbHelper;
import com.squareup.sqlbrite.BriteDatabase;

/**
 * Created by ej on 2/23/2017.
 */

public class DbCommon {
    public static void deleteDatabase(Context context) {
        context.deleteDatabase(LocalDbHelper.DB_NAME);
    }

    public static void closeDatabase() {
        DatabaseManager.getInstance().closeDatabase();
    }

    public static void initializeDatabase(Context context) {
        DatabaseManager.initialize(LocalDbHelper.getInstance(context));
    }

    public static BriteDatabase getOpenedDatabase() {
        return DatabaseManager.getInstance().openDatabase();
    }

    public static SQLiteDatabase givenWritableDatabase(Context context) {
        return LocalDbHelper.getInstance(context).getWritableDatabase();
    /*DatabaseManager.initialize(DelightfulOpenHelper.getInstance(context));*/
        //return DatabaseManager.getInstance().openDatabase();
    }
}

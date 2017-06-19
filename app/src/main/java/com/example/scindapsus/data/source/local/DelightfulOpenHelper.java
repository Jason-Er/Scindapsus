package com.example.scindapsus.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.scindapsus.data.populator.UserPopulator;
import com.example.scindapsus.model.LineModel;
import com.example.scindapsus.model.PlayModel;
import com.example.scindapsus.model.RoleModel;
import com.example.scindapsus.model.SceneModel;

/**
 * Created by ej on 2/22/2017.
 */

public class DelightfulOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "apricot.db";
    private static final int DB_VERSION = 1;

    public DelightfulOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL(UserModel.CREATE_TABLE);
        db.execSQL(PlayModel.CREATE_TABLE);
        db.execSQL(SceneModel.CREATE_TABLE);
        db.execSQL(LineModel.CREATE_TABLE);
        db.execSQL(RoleModel.CREATE_TABLE);
        // populate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    private void populate(SQLiteDatabase db) {
        UserPopulator.populate(db);
    }

}

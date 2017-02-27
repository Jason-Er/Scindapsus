package com.example.scindapsus.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.model.User;
import com.example.scindapsus.model.UserModel;
import com.example.scindapsus.runner.CustomRunner;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * Created by ej on 2/23/2017.
 */

public class UserTableShould extends CustomRunner {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        DbCommon.deleteDatabase(context);
    }

    @Test
    public void have_created_all_columns() throws Exception {
        HashSet<String> columns = givenAuthorColumns();
        SQLiteDatabase db = givenWritableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + UserModel.TABLE_NAME + ")", null);

        cursor.moveToFirst();
        int columnNameIndex = cursor.getColumnIndex("name");
        do {
            String columnName = cursor.getString(columnNameIndex);
            columns.remove(columnName);
        } while (cursor.moveToNext());

        assertTrue(columns.isEmpty());
        cursor.close();
        db.close();
    }

    @Test
    public void be_able_to_return_cursor_with_all_default_authors() throws Exception {
        SQLiteDatabase db = givenWritableDatabase();

        Cursor cursor = db.rawQuery(UserModel.SELECT_ALL, new String[0]);

        int AUTHOR_COUNT = 1;
        assertTrue(cursor.getCount() == AUTHOR_COUNT);
    }

    @Test
    public void map_cursor_with_domain_model() throws Exception {
        SQLiteDatabase db = givenWritableDatabase();

        Cursor cursor = db.rawQuery(UserModel.SELECT_ALL, new String[0]);
        cursor.moveToFirst();

//        User author = User.MAPPER.map(cursor);
//        assertNotNull(author);
    }

    private SQLiteDatabase givenWritableDatabase() {
        return DbCommon.givenWritableDatabase(context);
    }

    private HashSet<String> givenAuthorColumns() {
        HashSet<String> columns = new HashSet<>();
        columns.add(UserModel._ID);
        columns.add(UserModel.NAME);
        columns.add(UserModel.PASSWORD);
        return columns;
    }
}


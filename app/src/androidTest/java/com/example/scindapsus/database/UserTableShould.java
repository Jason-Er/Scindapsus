package com.example.scindapsus.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.model.User;
import com.example.scindapsus.model.UserModel;
import com.example.scindapsus.runner.CustomRunner;
import com.squareup.sqlbrite.BriteDatabase;

import rx.functions.Action1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertFalse;
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

        List<User> result = new ArrayList<>();
        Cursor cursor = db.rawQuery(UserModel.SELECT_ALL, new String[0]);
        while (cursor.moveToNext()) {
            result.add(User.SELECT_ALL_MAPPER.map(cursor));
        }
        assertFalse(result.isEmpty());
    }

    @Test
    public void map_cursor_with_domain_model_rx() throws Exception {
        BriteDatabase db = givenBriteDatabase();

        db.createQuery(User.TABLE_NAME, User.SELECT_ALL)
        .mapToList(User.RX_SELECT_ALL_MAPPER)
        .subscribe(new Action1<List<User>>() {
            @Override
            public void call(List<User> users) {
                for(User user:users) {
                    System.out.println(user.toString()+"++++++++++++++++++");
                }
            }
        });

    }

    private BriteDatabase givenBriteDatabase() {
        return DbCommon.getOpenedDatabase();
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


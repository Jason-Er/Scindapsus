package com.example.scindapsus.global;

import android.app.Application;
import android.content.Context;

import com.example.scindapsus.data.source.local.DatabaseManager;
import com.example.scindapsus.data.source.local.LocalDbHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;

/**
 * Created by ej on 2/23/2017.
 */

public class ScindapsusApplication extends Application {

    BriteDatabase briteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.initialize(LocalDbHelper.getInstance(this));
    }

}

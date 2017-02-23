package com.example.scindapsus.global;

import android.app.Application;

import com.example.scindapsus.data.source.local.DatabaseManager;
import com.example.scindapsus.data.source.local.LocalDbHelper;

/**
 * Created by ej on 2/23/2017.
 */

public class ScindapsusApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.initialize(LocalDbHelper.getInstance(this));
    }

}

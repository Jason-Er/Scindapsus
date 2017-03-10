package com.example.scindapsus.global;

import android.app.Application;

/**
 * Created by ej on 2/23/2017.
 */

public class ScindapsusApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // DatabaseManager.initialize(LocalDbHelper.getInstance(this));
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return mApplicationComponent;
    }
}

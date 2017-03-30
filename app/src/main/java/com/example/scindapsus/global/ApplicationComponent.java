package com.example.scindapsus.global;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.Properties;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ej on 3/9/2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Properties getProperties();
    Application getApplication();
    SharedPreferences getSharedPreferences();

    void inject(BaseActivity baseActivity);

}

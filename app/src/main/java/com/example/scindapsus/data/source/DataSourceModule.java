package com.example.scindapsus.data.source;

import android.app.Application;

import com.example.scindapsus.data.source.remote.browse.BrowseHttpImpl;
import com.example.scindapsus.data.source.remote.login.LoginHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.util.DataSourceScope;

import java.util.Properties;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/3/2017.
 */
@Module
public class DataSourceModule {
    @DataSourceScope
    @Provides
    public LoginHttpImpl provideLoginHttpImpl(ApplicationComponent applicationComponent){
        return new LoginHttpImpl(applicationComponent);
    }
    @DataSourceScope
    @Provides
    public BrowseHttpImpl provideBrowseHttpImpl(ApplicationComponent applicationComponent){
        return new BrowseHttpImpl(applicationComponent);
    }
}

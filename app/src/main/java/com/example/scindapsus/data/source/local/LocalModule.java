package com.example.scindapsus.data.source.local;

import android.content.Context;

import com.example.scindapsus.util.label.DataSourceFuncModelScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 5/25/2017.
 */

@Module
public class LocalModule {
    @DataSourceFuncModelScope
    @Provides
    public DelightfulOpenHelper provideDelightfulOpenHelper(Context context) {
        return new DelightfulOpenHelper(context);
    }
}

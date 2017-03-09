package com.example.scindapsus.global;

import android.content.Context;
import com.example.scindapsus.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/7/2017.
 */
@Module
public final class ApplicationModule {
    private final Context mContext;
    private final Properties mProperties;

    ApplicationModule(Context context) {
        this.mContext = context;
        this.mProperties = new Properties();
        InputStream is = context.getResources().openRawResource(R.raw.config);
        try {
            mProperties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    public Properties provideProperties(){
        return mProperties;
    }

}

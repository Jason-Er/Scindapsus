package com.example.scindapsus.global;

import android.app.Application;
import com.example.scindapsus.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/7/2017.
 */
@Module
public final class ApplicationModule {
    private final Application application;
    private final Properties properties;

    ApplicationModule(Application application) {
        this.application = application;
        this.properties = new Properties();
        InputStream is = application.getResources().openRawResource(R.raw.config);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    public Properties provideProperties(){
        return properties;
    }

}

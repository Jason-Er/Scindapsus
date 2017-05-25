package com.example.scindapsus.data.source;

import com.example.scindapsus.data.source.local.participate.ParticipateImpl;
import com.example.scindapsus.data.source.remote.browse.BrowseHttpImpl;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.data.source.remote.image.ImageHttpImpl;
import com.example.scindapsus.data.source.remote.login.LoginHttpImpl;
import com.example.scindapsus.data.source.remote.participate.ParticipateHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.util.label.DataSourceScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 3/3/2017.
 */
@Module
public class DataSourceModule {
    // http impl
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
    @DataSourceScope
    @Provides
    public ImageHttpImpl provideImageHttpImpl(ApplicationComponent applicationComponent){
        return new ImageHttpImpl(applicationComponent);
    }
    @DataSourceScope
    @Provides
    public ParticipateHttpImpl provideParticipateHttpImpl(ApplicationComponent applicationComponent){
        return new ParticipateHttpImpl(applicationComponent);
    }
    @DataSourceScope
    @Provides
    public FileHttpImpl provideFileHttpImpl(ApplicationComponent applicationComponent){
        return new FileHttpImpl(applicationComponent);
    }

    // local impl
    @DataSourceScope
    @Provides
    public ParticipateImpl provideParticipateImpl(ApplicationComponent applicationComponent){
        return new ParticipateImpl(applicationComponent);
    }
}

package com.example.scindapsus.data.source.remote;

import com.example.scindapsus.data.source.remote.browse.BrowseHttpImpl;
import com.example.scindapsus.data.source.remote.file.FileHttpImpl;
import com.example.scindapsus.data.source.remote.image.ImageHttpImpl;
import com.example.scindapsus.data.source.remote.login.LoginHttpImpl;
import com.example.scindapsus.data.source.remote.participate.ParticipateHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.util.label.DataSourceFuncModelScope;

import dagger.Component;

/**
 * Created by ej on 3/6/2017.
 */
@DataSourceFuncModelScope
@Component(modules = {HttpModule.class}, dependencies = ApplicationComponent.class)
public interface HttpComponent {
    void inject(LoginHttpImpl loginHttp);
    void inject(BrowseHttpImpl browseHttp);
    void inject(ImageHttpImpl imageHttp);
    void inject(ParticipateHttpImpl participateHttp);
    void inject(FileHttpImpl fileHttp);
}

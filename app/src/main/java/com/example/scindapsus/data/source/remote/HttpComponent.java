package com.example.scindapsus.data.source.remote;

import com.example.scindapsus.data.source.remote.login.LoginHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.util.DataSourceFuncModelScope;

import dagger.Component;

/**
 * Created by ej on 3/6/2017.
 */
@DataSourceFuncModelScope
@Component(modules = {HttpModule.class}, dependencies = ApplicationComponent.class)
public interface HttpComponent {
    void inject(LoginHttpImpl loginHttp);
}

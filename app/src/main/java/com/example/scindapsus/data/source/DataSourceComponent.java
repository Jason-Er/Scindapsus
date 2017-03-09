package com.example.scindapsus.data.source;

import com.example.scindapsus.service.login.LoginServiceImpl;
import com.example.scindapsus.util.DataSourceScope;

import dagger.Component;

/**
 * Created by ej on 3/3/2017.
 */
@DataSourceScope
@Component(modules={DataSourceModule.class})
public interface DataSourceComponent {
    void inject(LoginServiceImpl loginServiceImpl);
}

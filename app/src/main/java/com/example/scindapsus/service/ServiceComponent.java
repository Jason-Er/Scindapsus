package com.example.scindapsus.service;

import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.util.custom.browseComponent.BrowseRVAdapter;
import com.example.scindapsus.util.custom.sceneComponent.SceneRVAdapter;
import com.example.scindapsus.util.label.ServiceScope;
import com.example.scindapsus.vp.browse.BrowsePresenter;
import com.example.scindapsus.vp.login.LoginPresenter;
import com.example.scindapsus.vp.participate.ParticipatePresenter;

import dagger.Component;

/**
 * Created by ej on 3/3/2017.
 */
@ServiceScope
@Component(modules = {ServiceModule.class}, dependencies = ApplicationComponent.class)
public interface ServiceComponent {
    void inject(LoginPresenter presenter);
    void inject(BrowsePresenter presenter);
    void inject(ParticipatePresenter presenter);

    void inject(BrowseRVAdapter browseRVAdapter);
    void inject(SceneRVAdapter sceneRVAdapter);
}

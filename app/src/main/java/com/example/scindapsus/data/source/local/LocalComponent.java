package com.example.scindapsus.data.source.local;

import com.example.scindapsus.data.source.local.participate.ParticipateImpl;
import com.example.scindapsus.data.source.local.scene.SceneImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.util.label.DataSourceFuncModelScope;

import dagger.Component;

/**
 * Created by ej on 5/25/2017.
 */
@DataSourceFuncModelScope
@Component(modules = {LocalModule.class}, dependencies = ApplicationComponent.class)
public interface LocalComponent {
    void inject(ParticipateImpl participateImpl);
    void inject(SceneImpl participateImpl);
}

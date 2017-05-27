package com.example.scindapsus.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by ej on 5/27/2017.
 */
@AutoValue
public abstract class SceneM implements SceneModel{
    public static final Factory<SceneM> FACTORY = new SceneModel.Factory<>(new Creator<SceneM>(){
        @Override
        public SceneM create(@Nullable Long ordinal, long play_id, long id, @Nullable String name) {
            return new AutoValue_SceneM(ordinal, play_id, id, name);
        }
    });
}

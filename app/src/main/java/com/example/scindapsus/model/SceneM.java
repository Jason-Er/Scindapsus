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
        public SceneM create(long id, @Nullable String name, @Nullable Long ordinal, long play_id) {
            return new AutoValue_SceneM(id, name, ordinal, play_id);
        }
    });
}

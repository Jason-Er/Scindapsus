package com.example.scindapsus.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by ej on 5/3/2017.
 */
@AutoValue
public abstract class Scene implements SceneModel{
    @Nullable
    public abstract List<Line> lines();
    public static Scene create(long id, String name, Long ordinal, long play_id, List<Line> lines) {
        return new AutoValue_Scene(id, name, ordinal, play_id, lines);
    }
    public static TypeAdapter<Scene> typeAdapter(Gson gson) {
        return new AutoValue_Scene.GsonTypeAdapter(gson);
    }
    public static final Factory<Scene>  FACTORY = new Factory<>(new Creator<Scene>(){
        @Override
        public Scene create(long id, @Nullable String name, long ordinal, long play_id) {
            return new AutoValue_Scene(id, name, ordinal, play_id, null);
        }
    });
}

package com.example.scindapsus.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ej on 6/1/2017.
 */
@AutoValue
public abstract class UploadVoiceUrl {
    public abstract String play_name_id();
    public abstract String scene_ordinal();
    public abstract String line_ordinal();
    public abstract String url();
    public static UploadVoiceUrl create(String playNameId, String sceneOrdinal, String lineOrdinal, String url) {
        return new AutoValue_UploadVoiceUrl(playNameId, sceneOrdinal, lineOrdinal, url);
    }
    public static TypeAdapter<UploadVoiceUrl> typeAdapter(Gson gson) {
        return new AutoValue_UploadVoiceUrl.GsonTypeAdapter(gson);
    }
}

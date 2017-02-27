package com.example.scindapsus.model.adapter;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by ej on 2/27/2017.
 */
@GsonTypeAdapterFactory
public abstract class StatusAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_StatusAdapterFactory();
    }
}

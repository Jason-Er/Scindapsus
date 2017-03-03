package com.example.scindapsus.model.adapter;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by ej on 3/1/2017.
 */
@GsonTypeAdapterFactory
public abstract class HttpResultAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_HttpResultAdapterFactory();
    }
}

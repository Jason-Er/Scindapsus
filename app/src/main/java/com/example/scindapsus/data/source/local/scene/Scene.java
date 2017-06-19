package com.example.scindapsus.data.source.local.scene;

import com.example.scindapsus.model.Line;

import io.reactivex.Observable;

/**
 * Created by ej on 6/7/2017.
 */

public interface Scene {
    Observable<Line> saveLine(Line line);
}

package com.example.scindapsus.data.source.local.scene;

import com.example.scindapsus.model.LineM;

import io.reactivex.Observable;

/**
 * Created by ej on 6/7/2017.
 */

public interface Scene {
    Observable<LineM> saveLineM(LineM lineM);
}

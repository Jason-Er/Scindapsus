package com.example.scindapsus.data.source.local.participate;

import com.example.scindapsus.model.Play;

import io.reactivex.Observable;

/**
 * Created by ej on 5/25/2017.
 */

public interface Participate {
    Observable<Play> loadPlay(int id);
    Observable<Play> savePlay(Play play);
}

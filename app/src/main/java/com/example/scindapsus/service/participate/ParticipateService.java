package com.example.scindapsus.service.participate;

import com.example.scindapsus.model.Play;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by ej on 5/3/2017.
 */

public interface ParticipateService {
    Observable<Play> loadPlay(String token, int id);
}

package com.example.scindapsus.service.participate;

import com.example.scindapsus.model.Play;

import io.reactivex.Maybe;

/**
 * Created by ej on 5/3/2017.
 */

public interface ParticipateService {
    Maybe<Play> loadPlay(String token, int id);
}

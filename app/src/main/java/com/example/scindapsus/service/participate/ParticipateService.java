package com.example.scindapsus.service.participate;

import com.example.scindapsus.model.Play;

import rx.Subscriber;

/**
 * Created by ej on 5/3/2017.
 */

public interface ParticipateService {
    void loadPlay(String token, Subscriber<Play> subscriber, int id);
}

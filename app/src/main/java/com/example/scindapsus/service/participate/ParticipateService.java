package com.example.scindapsus.service.participate;

import com.example.scindapsus.model.Play;

import io.reactivex.Observer;

/**
 * Created by ej on 5/3/2017.
 */

public interface ParticipateService {
    void loadPlay(String token, Observer<Play> observer, int id);
}

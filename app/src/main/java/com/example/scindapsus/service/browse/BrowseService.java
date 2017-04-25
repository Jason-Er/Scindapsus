package com.example.scindapsus.service.browse;

import com.example.scindapsus.model.Token;

import rx.Subscriber;

/**
 * Created by ej on 4/25/2017.
 */

public interface BrowseService {
    void loadPlaysInfo(Subscriber<Token> subscriber, int page);
}

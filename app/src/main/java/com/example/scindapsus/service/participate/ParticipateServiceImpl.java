package com.example.scindapsus.service.participate;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.participate.ParticipateHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Play;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ej on 5/3/2017.
 */

public class ParticipateServiceImpl implements ParticipateService {
    @Inject
    ParticipateHttpImpl participateHttpImpl;

    public ParticipateServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }
    @Override
    public void loadPlay(String token, Subscriber<Play> subscriber, int id) {
        participateHttpImpl.loadPlay(token, subscriber, id);
    }
}

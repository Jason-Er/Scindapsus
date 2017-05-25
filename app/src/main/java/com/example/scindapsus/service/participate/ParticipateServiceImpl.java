package com.example.scindapsus.service.participate;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.local.participate.ParticipateImpl;
import com.example.scindapsus.data.source.remote.participate.ParticipateHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Play;

import javax.inject.Inject;

import io.reactivex.Observer;

/**
 * Created by ej on 5/3/2017.
 */

public class ParticipateServiceImpl implements ParticipateService {

    @Inject
    ParticipateImpl participateImpl;

    @Inject
    ParticipateHttpImpl participateHttpImpl;

    public ParticipateServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }
    @Override
    public void loadPlay(String token, Observer<Play> observer, int id) {
        participateImpl.loadPlay(id);
        participateHttpImpl.loadPlay(token, observer, id);
    }
}

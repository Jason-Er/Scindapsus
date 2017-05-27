package com.example.scindapsus.service.participate;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.local.participate.ParticipateImpl;
import com.example.scindapsus.data.source.remote.participate.ParticipateHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Play;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    public Observable<Play> loadPlay(String token, int id) {
        return participateImpl.loadPlay(id);

/*
        return participateHttpImpl.loadPlay(token, id)
                .flatMap(new Function<Play, ObservableSource<Play>>() {
                    @Override
                    public ObservableSource<Play> apply(@NonNull Play play) throws Exception {
                        return participateImpl.savePlay(play);
                    }
                });

*/
    }
}

package com.example.scindapsus.data.source.local.participate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.data.source.local.DaggerLocalComponent;
import com.example.scindapsus.data.source.local.DelightfulOpenHelper;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.Playm;
import com.squareup.sqldelight.SqlDelightStatement;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by ej on 5/25/2017.
 */

public class ParticipateImpl implements Participate {

    @Inject
    DelightfulOpenHelper delightfulOpenHelper;

    public ParticipateImpl(ApplicationComponent applicationComponent) {
        DaggerLocalComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public Observable<Play> loadPlay(int id) {
        SQLiteDatabase db = delightfulOpenHelper.getReadableDatabase();
        SqlDelightStatement query = Playm.FACTORY.select_by_id(id);
        Cursor cursor = db.rawQuery(query.statement, query.args);
        if(cursor.moveToFirst()) {
            Playm playm = Playm.FACTORY.select_by_idMapper().map(cursor);
        }
        return null;
    }

    @Override
    public Observable<Play> savePlay(final Play play) {
        return Observable.create(new ObservableOnSubscribe<Play>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Play> observableEmitter) throws Exception {
                SQLiteDatabase db = delightfulOpenHelper.getWritableDatabase();
                SqlDelightStatement query = Playm.FACTORY.select_by_id(play.getId());
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {
                    Playm.UpdateOnePlay updateOnePlay = new Playm.UpdateOnePlay(db);
                    updateOnePlay.bind(play.getName(), play.getExtract(), play.getStillUrl(), play.getId());
                    int updated = updateOnePlay.program.executeUpdateDelete();
                } else {
                    Playm.InsertOnePlay insertOnePlay = new Playm.InsertOnePlay(db);
                    insertOnePlay.bind(play.getId(), play.getName(), play.getExtract(), play.getStillUrl());
                    int updated = insertOnePlay.program.executeUpdateDelete();
                }
                observableEmitter.onNext(play);
                observableEmitter.onComplete();
            }
        });
    }
}
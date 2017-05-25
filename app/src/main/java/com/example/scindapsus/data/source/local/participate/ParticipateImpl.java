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
        Playm playm = Playm.FACTORY.select_by_idMapper().map(cursor);
        return null;
    }
}

package com.example.scindapsus.data.source.local.participate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.data.source.local.DaggerLocalComponent;
import com.example.scindapsus.data.source.local.DelightfulOpenHelper;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.LineM;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.PlayM;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.model.SceneM;
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
        SqlDelightStatement query = PlayM.FACTORY.select_by_id(id);
        Cursor cursor = db.rawQuery(query.statement, query.args);
        if(cursor.moveToFirst()) {
            PlayM playm = PlayM.FACTORY.select_by_idMapper().map(cursor);
        }
        return null;
    }

    @Override
    public Observable<Play> savePlay(final Play play) {
        return Observable.create(new ObservableOnSubscribe<Play>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Play> observableEmitter) throws Exception {
                SQLiteDatabase db = delightfulOpenHelper.getWritableDatabase();
                SqlDelightStatement query = PlayM.FACTORY.select_by_id(play.getId());
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {
                    PlayM.UpdateOnePlay updateOnePlay = new PlayM.UpdateOnePlay(db);
                    updateOnePlay.bind(play.getName(), play.getExtract(), play.getStillUrl(), play.getId());
                    int updated = updateOnePlay.program.executeUpdateDelete();
                } else {
                    PlayM.InsertOnePlay insertOnePlay = new PlayM.InsertOnePlay(db);
                    insertOnePlay.bind(play.getId(), play.getName(), play.getExtract(), play.getStillUrl());
                    int updated = insertOnePlay.program.executeUpdateDelete();
                }

                // save scene
                for(Scene scene: play.getScenes()) {
                    query = SceneM.FACTORY.select_by_id(scene.getId());
                    cursor = db.rawQuery(query.statement, query.args);
                    if(cursor.moveToFirst()) {
                        SceneM.UpdateOneScene updateOneScene = new SceneM.UpdateOneScene(db);
                        updateOneScene.bind(scene.getName(), scene.getId(), scene.getOrdinal(), scene.getId());
                        int updated = updateOneScene.program.executeUpdateDelete();
                    } else {
                        SceneM.InsertOneScene insertOneScene = new SceneM.InsertOneScene(db);
                        insertOneScene.bind(scene.getId(), scene.getName(), scene.getOrdinal(), scene.getId());
                        int updated = insertOneScene.program.executeUpdateDelete();
                    }

                    // save line
                    for(Line line: scene.getLines()) {
                        query = LineM.FACTORY.select_by_id(line.getId());
                        cursor = db.rawQuery(query.statement, query.args);
                        if(cursor.moveToFirst()) {
                            LineM.UpdateOneLine updateOneLine = new LineM.UpdateOneLine(db);
                            updateOneLine.bind(line.getText(), line.getOrdinal(), line.getAudioURL(), scene.getId(), line.getId());
                            int updated = updateOneLine.program.executeUpdateDelete();
                        } else {
                            LineM.InsertOneLine insertOneLine = new LineM.InsertOneLine(db);
                            insertOneLine.bind(line.getId(), line.getText(), line.getOrdinal(), line.getAudioURL(), line.getId());
                            int updated = insertOneLine.program.executeUpdateDelete();
                        }
                    }
                }

                observableEmitter.onNext(play);
                observableEmitter.onComplete();
            }
        });
    }
}
package com.example.scindapsus.data.source.local.participate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by ej on 5/25/2017.
 */

public class ParticipateImpl implements Participate {
    final static String TAG = ParticipateImpl.class.getName();

    @Inject
    DelightfulOpenHelper delightfulOpenHelper;

    public ParticipateImpl(ApplicationComponent applicationComponent) {
        DaggerLocalComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public Observable<Play> loadPlay(final int id) {
        Log.i(TAG, "loadPlay");
        return Observable.create(new ObservableOnSubscribe<Play>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Play> observableEmitter) throws Exception {
                Play play = new Play();
                SQLiteDatabase db = delightfulOpenHelper.getReadableDatabase();
                SqlDelightStatement query = PlayM.FACTORY.select_by_id(id);
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {

                    PlayM playm = PlayM.FACTORY.select_by_idMapper().map(cursor);

                    play.setName(playm.name());
                    play.setId(playm.id());
                    play.setExtract(playm.extract());
                    play.setStillUrl(playm.still_url());

                    cursor.close();
                    query = SceneM.FACTORY.select_by_play_id(playm.id());
                    cursor = db.rawQuery(query.statement, query.args);
                    List<SceneM> sceneMList = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        sceneMList.add(SceneM.FACTORY.select_by_idMapper().map(cursor));
                    }

                    List<Scene> sceneList = new ArrayList<>(cursor.getCount());
                    for(SceneM sceneM: sceneMList) {
                        Scene scene = new Scene();
                        scene.setId(sceneM.id());
                        scene.setName(sceneM.name());
                        scene.setOrdinal(sceneM.ordinal());
                        scene.setPlayId(sceneM.play_id());
                        sceneList.add(scene);
                    }
                    play.setScenes(sceneList);

                    for(Scene scene: sceneList) {
                        cursor.close();
                        query = LineM.FACTORY.select_by_scene_id(scene.getId());
                        cursor = db.rawQuery(query.statement, query.args);
                        List<LineM> lineMList = new ArrayList<LineM>(cursor.getCount());
                        while (cursor.moveToNext()) {
                            lineMList.add(LineM.FACTORY.select_by_idMapper().map(cursor));
                        }
                        List<Line> lineList = new ArrayList<Line>(cursor.getCount());
                        for(LineM lineM: lineMList) {
                            Line line = new Line();
                            line.setId(lineM.id());
                            line.setOrdinal(lineM.ordinal());
                            line.setAudioURL(lineM.audiourl());
                            line.setText(lineM.text());
                            line.setSceneId(lineM.scene_id());
                            lineList.add(line);
                        }
                        scene.setLines(lineList);
                    }
                }

                cursor.close();
                db.close();
                observableEmitter.onNext(play);
                observableEmitter.onComplete();
            }
        });
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
                    updateOnePlay.program.executeUpdateDelete();
                } else {
                    PlayM.InsertOnePlay insertOnePlay = new PlayM.InsertOnePlay(db);
                    insertOnePlay.bind(play.getId(), play.getName(), play.getExtract(), play.getStillUrl());
                    insertOnePlay.program.executeUpdateDelete();
                }

                // save scene
                for(Scene scene: play.getScenes()) {
                    cursor.close();
                    query = SceneM.FACTORY.select_by_id(scene.getId());
                    cursor = db.rawQuery(query.statement, query.args);
                    if(cursor.moveToFirst()) {
                        SceneM.UpdateOneScene updateOneScene = new SceneM.UpdateOneScene(db);
                        updateOneScene.bind(scene.getName(), scene.getOrdinal(), scene.getPlayId(), scene.getId());
                        updateOneScene.program.executeUpdateDelete();
                    } else {
                        SceneM.InsertOneScene insertOneScene = new SceneM.InsertOneScene(db);
                        insertOneScene.bind(scene.getId(), scene.getName(), scene.getOrdinal(), scene.getPlayId());
                        insertOneScene.program.executeUpdateDelete();
                    }

                    // save line
                    for(Line line: scene.getLines()) {
                        cursor.close();
                        query = LineM.FACTORY.select_by_id(line.getId());
                        cursor = db.rawQuery(query.statement, query.args);
                        if(cursor.moveToFirst()) {
                            LineM.UpdateOneLine updateOneLine = new LineM.UpdateOneLine(db);
                            updateOneLine.bind(line.getOrdinal(), line.getText(), line.getAudioURL(), line.getSceneId(), line.getId());
                            updateOneLine.program.executeUpdateDelete();
                        } else {
                            LineM.InsertOneLine insertOneLine = new LineM.InsertOneLine(db);
                            insertOneLine.bind(line.getId(), line.getOrdinal(), line.getText(), line.getAudioURL(), line.getSceneId());
                            insertOneLine.program.executeUpdateDelete();
                        }
                    }
                }

                cursor.close();
                db.close();
                observableEmitter.onNext(play);
                observableEmitter.onComplete();
            }
        });

    }
}
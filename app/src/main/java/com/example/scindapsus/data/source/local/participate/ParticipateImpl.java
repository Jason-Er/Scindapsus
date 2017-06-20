package com.example.scindapsus.data.source.local.participate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.scindapsus.data.source.local.DaggerLocalComponent;
import com.example.scindapsus.data.source.local.DelightfulOpenHelper;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.Role;
import com.example.scindapsus.model.Scene;
import com.example.scindapsus.model.Voice;
import com.squareup.sqldelight.SqlDelightStatement;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
                SQLiteDatabase db = delightfulOpenHelper.getReadableDatabase();
                SqlDelightStatement query = Play.FACTORY.select_by_id(id);
                Play playQ = Play.create(0,"","","", new ArrayList<Scene>(), new ArrayList<Role>());
                List<Scene> sceneListQ = null;
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {
                    Play play = Play.FACTORY.select_by_idMapper().map(cursor);

                    //query roles
                    cursor.close();
                    query = Role.FACTORY.select_by_id(play.id());
                    cursor = db.rawQuery(query.statement, query.args);
                    List<Role> cast = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        cast.add(Role.FACTORY.select_by_idMapper().map(cursor));
                    }

                    //query scenes
                    cursor.close();
                    query = Scene.FACTORY.select_by_play_id(play.id());
                    cursor = db.rawQuery(query.statement, query.args);
                    List<Scene> sceneList = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        sceneList.add(Scene.FACTORY.select_by_idMapper().map(cursor));
                    }

                    sceneListQ = new ArrayList<>(cursor.getCount());
                    for(Scene scene: sceneList) {
                        //query lines
                        cursor.close();
                        query = Line.FACTORY.select_by_scene_id(scene.id());
                        cursor = db.rawQuery(query.statement, query.args);
                        List<Line> lineList = new ArrayList<>(cursor.getCount());
                        while (cursor.moveToNext()) {
                            lineList.add(Line.FACTORY.select_by_idMapper().map(cursor));
                        }
                        //query voices
                        List<Line> lineListWithVoice = new ArrayList<>();
                        for(Line line: lineList) {
                            cursor.close();
                            // TODO: 6/20/2017 userId may change according to the user login
                            query = Voice.FACTORY.select_by_two_id(findUserIdByRoleId(cast, line.role_id()), line.id());
                            cursor = db.rawQuery(query.statement, query.args);
                            if(cursor.moveToFirst()) {
                                Voice voice = Voice.FACTORY.select_by_idMapper().map(cursor);
                                lineListWithVoice.add(Line.create(line.id(), line.ordinal(), line.text(), line.audio_url(), line.role_id(), line.scene_id(), voice));
                            } else {
                                lineListWithVoice.add(Line.create(line.id(), line.ordinal(), line.text(), line.audio_url(), line.role_id(), line.scene_id(), null));
                            }
                        }
                        sceneListQ.add(Scene.create(scene.id(), scene.name(), scene.ordinal(), scene.play_id(), lineListWithVoice));
                    }

                    playQ = Play.create(play.id(), play.name(), play.extract(), play.still_url(), sceneListQ, cast);
                }

                cursor.close();
                db.close();
                observableEmitter.onNext(playQ);
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
                SqlDelightStatement query = Play.FACTORY.select_by_id(play.id());
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {
                    Play.UpdateOnePlay updateOnePlay = new Play.UpdateOnePlay(db);
                    updateOnePlay.bind(play.name(), play.extract(), play.still_url(), play.id());
                    updateOnePlay.program.executeUpdateDelete();
                } else {
                    Play.InsertOnePlay insertOnePlay = new Play.InsertOnePlay(db);
                    insertOnePlay.bind(play.id(), play.name(), play.extract(), play.still_url());
                    insertOnePlay.program.executeUpdateDelete();
                }

                // save cast
                for(Role role: play.cast()) {
                    cursor.close();
                    query = Role.FACTORY.select_by_id(role.id());
                    cursor = db.rawQuery(query.statement, query.args);
                    if(cursor.moveToFirst()) {
                        Role.UpdateOneRole updateOneRole = new Role.UpdateOneRole(db);
                        updateOneRole.bind(role.name(), role.age(), role.gender(), role.description(), role.user_id(), role.play_id(), role.id());
                        updateOneRole.program.executeUpdateDelete();
                    } else {
                        Role.InsertOneRole insertOneRole = new Role.InsertOneRole(db);
                        insertOneRole.bind(role.id(), role.name(), role.age(), role.gender(), role.description(), role.user_id(), role.play_id());
                        insertOneRole.program.executeUpdateDelete();
                    }
                }

                // save scene
                for(Scene scene: play.scenes()) {
                    cursor.close();
                    query = Scene.FACTORY.select_by_id(scene.id());
                    cursor = db.rawQuery(query.statement, query.args);
                    if(cursor.moveToFirst()) {
                        Scene.UpdateOneScene updateOneScene = new Scene.UpdateOneScene(db);
                        updateOneScene.bind(scene.name(), scene.ordinal(), scene.play_id(), scene.id());
                        updateOneScene.program.executeUpdateDelete();
                    } else {
                        Scene.InsertOneScene insertOneScene = new Scene.InsertOneScene(db);
                        insertOneScene.bind(scene.id(), scene.name(), scene.ordinal(), scene.play_id());
                        insertOneScene.program.executeUpdateDelete();
                    }

                    // save line
                    for(Line line: scene.lines()) {
                        cursor.close();
                        query = Line.FACTORY.select_by_id(line.id());
                        cursor = db.rawQuery(query.statement, query.args);
                        if(cursor.moveToFirst()) {
                            Line.UpdateOneLine updateOneLine = new Line.UpdateOneLine(db);
                            updateOneLine.bind(line.ordinal(), line.text(), line.audio_url(), line.role_id(), line.scene_id(), line.id());
                            updateOneLine.program.executeUpdateDelete();
                        } else {
                            Line.InsertOneLine insertOneLine = new Line.InsertOneLine(db);
                            insertOneLine.bind(line.id(), line.ordinal(), line.text(), line.audio_url(), line.role_id(), line.scene_id());
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

    private Long findUserIdByRoleId(List<Role> roles, Long roleId) {
        Long userId = 0L;
        for(Role role: roles) {
            if(role.id() == roleId) {
                userId = role.user_id();
                break;
            }
        }
        return userId;
    }
}
package com.example.scindapsus.data.source.local.scene;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.data.source.local.DaggerLocalComponent;
import com.example.scindapsus.data.source.local.DelightfulOpenHelper;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.Line;
import com.example.scindapsus.model.Play;
import com.example.scindapsus.model.Role;
import com.example.scindapsus.model.Voice;
import com.squareup.sqldelight.SqlDelightStatement;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by ej on 6/7/2017.
 */

public class SceneImpl implements Scene{
    @Inject
    DelightfulOpenHelper delightfulOpenHelper;

    public SceneImpl(ApplicationComponent applicationComponent) {
        DaggerLocalComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }

    @Override
    public Observable<Voice> saveVoice(final Voice voice) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter completableEmitter) throws Exception {
                SQLiteDatabase db = delightfulOpenHelper.getWritableDatabase();
                SqlDelightStatement query = Voice.FACTORY.select_by_two_id(voice.user_id(), voice.line_id());
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {
                    Voice.UpdateOneVoiceByTwoId updateOneVoiceByTwoId = new Voice.UpdateOneVoiceByTwoId(db);
                    updateOneVoiceByTwoId.bind(voice.audiourl_local(), voice.user_id(), voice.line_id());
                    updateOneVoiceByTwoId.program.executeUpdateDelete();
                } else {
                    Voice.InsertOneVoice insertOneVoice = new Voice.InsertOneVoice(db);
                    insertOneVoice.bind(voice.audiourl_local(), voice.user_id(), voice.line_id());
                    insertOneVoice.program.executeUpdateDelete();
                }
                cursor.close();
                db.close();
                completableEmitter.onNext(voice);
                completableEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Role> findRoleByRoleId(final Long roleId) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter observableEmitter) throws Exception {
                Role role = null;
                SQLiteDatabase db = delightfulOpenHelper.getWritableDatabase();
                SqlDelightStatement query = Role.FACTORY.select_by_id(roleId);
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {
                    role = Role.FACTORY.select_by_idMapper().map(cursor);
                }
                cursor.close();
                db.close();
                observableEmitter.onNext(role);
                observableEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Play> findPlayByVoice(Voice voice) {
        return null;
    }

    @Override
    public Observable<Play> findPlayByScene(com.example.scindapsus.model.Scene scene) {
        return null;
    }

    @Override
    public Observable<com.example.scindapsus.model.Scene> findSceneByLine(Line line) {
        return null;
    }

    @Override
    public Observable<Line> findLineByVoice(Voice voice) {
        return null;
    }
}

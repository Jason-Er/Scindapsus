package com.example.scindapsus.data.source.local.scene;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.scindapsus.data.source.local.DaggerLocalComponent;
import com.example.scindapsus.data.source.local.DelightfulOpenHelper;
import com.example.scindapsus.global.ApplicationComponent;
import com.example.scindapsus.model.LineM;
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
    public Observable<LineM> saveLineM(final LineM lineM) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter completableEmitter) throws Exception {
                SQLiteDatabase db = delightfulOpenHelper.getWritableDatabase();
                SqlDelightStatement query = LineM.FACTORY.select_by_id(lineM.id());
                Cursor cursor = db.rawQuery(query.statement, query.args);
                if(cursor.moveToFirst()) {
                    LineM.UpdateOneLine updateOneLine = new LineM.UpdateOneLine(db);
                    updateOneLine.bind(lineM.text(), lineM.audiourl(), lineM.audiourl_local(), lineM.ordinal(), lineM.scene_id(), lineM.id());
                    updateOneLine.program.executeUpdateDelete();
                } else {
                    LineM.InsertOneLine insertOneLine = new LineM.InsertOneLine(db);
                    insertOneLine.bind(lineM.id(), lineM.text(), lineM.audiourl(), lineM.audiourl_local(), lineM.ordinal(), lineM.scene_id());
                    insertOneLine.program.executeUpdateDelete();
                }
                cursor.close();
                db.close();
                completableEmitter.onNext(lineM);
                completableEmitter.onComplete();
            }
        });
    }
}

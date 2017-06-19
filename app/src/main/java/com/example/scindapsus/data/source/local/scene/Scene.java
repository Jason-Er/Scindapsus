package com.example.scindapsus.data.source.local.scene;

import com.example.scindapsus.model.Role;
import com.example.scindapsus.model.Voice;

import io.reactivex.Observable;

/**
 * Created by ej on 6/7/2017.
 */

public interface Scene {
    Observable<Voice> saveVoice(Voice voice);
    Observable<Role> findRoleByRoleId(final Long roleId);
}

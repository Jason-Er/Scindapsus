package com.example.scindapsus.global;

import android.support.annotation.NonNull;

/**
 * Created by ej on 2/21/2017.
 */

public interface BaseView<T> {
    void setPresenter(@NonNull T presenter);
}

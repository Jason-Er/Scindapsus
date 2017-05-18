package com.example.scindapsus.util.bus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sh on 2016/10/28 14:43.
 */

public class RxBusHelper {
    /*
    public static void post(Object o) {
        RxBus.getDefault().post(o);
    }


    public static <T> void doOnMainThread(Class<T> aClass, CompositeDisposable disposables, OnEventListener<T> listener) {
        disposables.add(RxBus.getDefault().toFlowable(aClass).observeOn(AndroidSchedulers.mainThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS))));
    }

    public static <T> void doOnMainThread(Class<T> aClass, OnEventListener<T> listener) {
        RxBus.getDefault().toFlowable(aClass).observeOn(AndroidSchedulers.mainThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS)));
    }


    public static <T> void doOnChildThread(Class<T> aClass, CompositeDisposable disposables, OnEventListener<T> listener) {
        disposables.add(RxBus.getDefault().toFlowable(aClass).subscribeOn(Schedulers.newThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS))));
    }

    public static <T> void doOnChildThread(Class<T> aClass, OnEventListener<T> listener) {
        RxBus.getDefault().toFlowable(aClass).subscribeOn(Schedulers.newThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS)));
    }

    public interface OnEventListener<T> {
        void onEvent(T t);

        void onError(ErrorBean errorBean);
    }
    */
}

package com.example.scindapsus.service.image;

import android.graphics.Bitmap;

import rx.Subscriber;

/**
 * Created by ej on 4/28/2017.
 */

public interface ImageService {
    void getImage(String token, Subscriber<Bitmap> subscriber, String url);
}

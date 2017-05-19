package com.example.scindapsus.service.image;

import android.graphics.Bitmap;

import io.reactivex.Observer;


/**
 * Created by ej on 4/28/2017.
 */

public interface ImageService {
    void getImage(String token, Observer<Bitmap> observer, String url);
}

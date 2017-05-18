package com.example.scindapsus.service.image;

import android.graphics.Bitmap;

import com.example.scindapsus.data.source.DaggerDataSourceComponent;
import com.example.scindapsus.data.source.remote.image.ImageHttpImpl;
import com.example.scindapsus.global.ApplicationComponent;

import org.reactivestreams.Subscriber;

import javax.inject.Inject;

import io.reactivex.Observer;

/**
 * Created by ej on 4/28/2017.
 */

public class ImageServiceImpl implements ImageService{
    @Inject
    ImageHttpImpl imageHttpImpl;

    public ImageServiceImpl(ApplicationComponent applicationComponent) {
        DaggerDataSourceComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);
    }
    @Override
    public void getImage(String token, Observer<Bitmap> observer, String url) {
        imageHttpImpl.getImage(token, observer, url);
    }
}

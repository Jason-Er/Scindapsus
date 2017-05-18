package com.example.scindapsus.data.source.remote.participate;

import com.example.scindapsus.model.Play;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ej on 5/3/2017.
 */

public interface ParticipateHttp {
    @GET("/v1/model/play/{id}")
    Flowable<Play> loadPlay(@Header("Authorization") String token, @Path("id") int id);
}

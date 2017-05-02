package com.example.scindapsus.data.source.remote.browse;

import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ej on 4/25/2017.
 */

public interface BrowseHttp {
    @GET("/v1/model/playsInfo")
    Observable<PageResult<List<PlayInfo>>> loadPlaysInfo(@Header("Authorization") String token, @Query("page") int page, @Query("size") int size);
    @GET("/v1/model/playsInfo")
    Observable<PageResult<List<PlayInfo>>> loadPlaysInfo(@Header("Authorization") String token);
}

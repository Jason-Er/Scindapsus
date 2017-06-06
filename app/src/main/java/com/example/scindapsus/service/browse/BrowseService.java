package com.example.scindapsus.service.browse;

import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ej on 4/25/2017.
 */

public interface BrowseService {
    Observable<PageResult<List<PlayInfo>>> loadPlaysInfo(String token, int page);
}

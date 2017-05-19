package com.example.scindapsus.service.browse;

import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by ej on 4/25/2017.
 */

public interface BrowseService {
    void loadPlaysInfo(String token, Observer<PageResult<List<PlayInfo>>> observer, int page);
}

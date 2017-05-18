package com.example.scindapsus.service.browse;

import com.example.scindapsus.model.PlayInfo;
import com.example.scindapsus.model.http.PageResult;

import org.reactivestreams.Subscriber;

import java.util.List;

/**
 * Created by ej on 4/25/2017.
 */

public interface BrowseService {
    void loadPlaysInfo(String token, Subscriber<PageResult<List<PlayInfo>>> observer, int page);
}

package com.xingqi.code.commondemo.mvp.model.api.service;

import com.xingqi.code.commondemo.mvp.model.entity.HotKeyWord;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HotKeyWordService {
    @GET("/data/hotKeyWordList")
    Observable<List<HotKeyWord>> getHotKeyWordList();
}

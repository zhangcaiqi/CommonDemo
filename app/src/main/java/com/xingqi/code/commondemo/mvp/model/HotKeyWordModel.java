package com.xingqi.code.commondemo.mvp.model;


import com.xingqi.code.commonlib.mvp.BaseModel;
import com.xingqi.code.commonlib.mvp.RepositoryManager;
import com.xingqi.code.commondemo.mvp.contract.HotKeyWordContract;
import com.xingqi.code.commondemo.mvp.model.api.Api;
import com.xingqi.code.commondemo.mvp.model.api.service.HotKeyWordService;
import com.xingqi.code.commondemo.mvp.model.entity.HotKeyWord;

import java.util.List;

import io.reactivex.Observable;

public class HotKeyWordModel extends BaseModel implements HotKeyWordContract.Model {
    @Override
    public Observable<List<HotKeyWord>> getHotKeyWordList() {
        HotKeyWordService service = RepositoryManager.getSingleton().obtainRetrofitService(Api.APP_DOMAIN,HotKeyWordService.class);
        return service.getHotKeyWordList();
    }
}

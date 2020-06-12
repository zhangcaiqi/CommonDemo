package com.xingqi.code.commondemo.mvp.contract;


import com.xingqi.code.commonlib.mvp.IModel;
import com.xingqi.code.commonlib.mvp.IView;
import com.xingqi.code.commondemo.mvp.model.entity.HotKeyWord;

import java.util.List;

import io.reactivex.Observable;

public interface HotKeyWordContract {

    interface View extends IView {
        void showKeyWord(List<HotKeyWord> hotKeyWordList);
    }
    interface Model extends IModel {
        Observable<List<HotKeyWord>> getHotKeyWordList();
    }
}

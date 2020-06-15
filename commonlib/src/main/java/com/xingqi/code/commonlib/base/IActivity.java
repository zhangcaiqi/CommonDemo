package com.xingqi.code.commonlib.base;

public interface IActivity extends IToolbar{

    int getLayoutId();

    boolean isRootPage();

    void release();

    void initData();

    boolean registerRxBus();

}

package com.xingqi.code.commonlib.base;

public interface IActivity extends IToolbar{

    boolean isRootPage();

    int getLayoutId();

    void release();

    void initData();

    boolean registerRxBus();

}

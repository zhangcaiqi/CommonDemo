package com.xingqi.code.commonlib.base;

public interface IToolbar {

    boolean hasToolbar();

    String toolbarTitle();

    int toolbarColor();

    boolean displayNavigateIcon();

    int navigateIconRes();

    boolean darkStatusBarText();

    void onNavigateClick();
}

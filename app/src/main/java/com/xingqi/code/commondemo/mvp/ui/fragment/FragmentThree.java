package com.xingqi.code.commondemo.mvp.ui.fragment;

import com.xingqi.code.commondemo.R;
import com.xingqi.code.commonlib.base.BaseFragment;
import com.xingqi.code.commonlib.mvp.BasePresenter;

public class FragmentThree extends BaseFragment {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    public void release() {

    }

    @Override
    public void initData() {

    }


    @Override
    public boolean hasToolbar() {
        return false;
    }

    @Override
    public String toolbarTitle() {
        return null;
    }
}

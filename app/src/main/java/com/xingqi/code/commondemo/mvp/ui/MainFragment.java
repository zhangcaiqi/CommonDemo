package com.xingqi.code.commondemo.mvp.ui;

import android.widget.Button;

import androidx.annotation.NonNull;

import com.threshold.rxbus2.RxBus;
import com.xingqi.code.commondemo.R;
import com.xingqi.code.commondemo.mvp.contract.HotKeyWordContract;
import com.xingqi.code.commondemo.mvp.model.HotKeyWordModel;
import com.xingqi.code.commondemo.mvp.model.entity.HotKeyWord;
import com.xingqi.code.commondemo.mvp.presenter.HotKeyWordPresenter;
import com.xingqi.code.commonlib.base.BaseFragment;
import com.xingqi.code.commonlib.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment<HotKeyWordPresenter> implements HotKeyWordContract.View {
    @BindView(R.id.send_msg_btn)
    Button sendMsgBtn;
    @BindView(R.id.jump_to_page)
    Button jumpToPage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void release() {

    }


    @Override
    public void initData() {
        mPresenter.getHotWordList();
    }

    @Override
    public boolean registerRxBus() {
        return false;
    }


    @Override
    public boolean hasToolbar() {
        return false;
    }

    @Override
    public String toolbarTitle() {
        return null;
    }

    @OnClick(R.id.send_msg_btn)
    public void onViewClicked() {
        RxBus.getDefault().post("12345");
    }

    @Override
    protected HotKeyWordPresenter initPresenter() {
        return new HotKeyWordPresenter(new HotKeyWordModel(), this);
    }

    @Override
    public void showKeyWord(List<HotKeyWord> hotKeyWordList) {
        for (HotKeyWord hotKeyWord : hotKeyWordList) {
            sendMsgBtn.setText(hotKeyWord.getKeyWordText());
        }
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @OnClick(R.id.jump_to_page)
    public void jumpToPage() {
        CommonUtils.startActivity(RecyclePaginateActivity.class);
    }
}

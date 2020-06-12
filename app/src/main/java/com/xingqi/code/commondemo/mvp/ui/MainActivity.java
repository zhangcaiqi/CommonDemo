package com.xingqi.code.commondemo.mvp.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.threshold.rxbus2.annotation.RxSubscribe;
import com.threshold.rxbus2.util.EventThread;
import com.xingqi.code.commondemo.R;
import com.xingqi.code.commondemo.mvp.contract.HotKeyWordContract;
import com.xingqi.code.commondemo.mvp.model.HotKeyWordModel;
import com.xingqi.code.commondemo.mvp.model.entity.HotKeyWord;
import com.xingqi.code.commondemo.mvp.presenter.HotKeyWordPresenter;
import com.xingqi.code.commonlib.base.BaseActivity;
import com.xingqi.code.commonlib.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<HotKeyWordPresenter> implements HotKeyWordContract.View {
    private static final String TAG = "MainActivity";
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected HotKeyWordPresenter initPresenter() {
        return new HotKeyWordPresenter(new HotKeyWordModel(), this);
    }

    @Override
    public boolean isRootPage() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void release() {

    }


    @Override
    public void initData() {
        mPresenter.getHotWordList();
        MainFragment mainFragment = MainFragment.getInstance(null,MainFragment.class);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment,"mainFragment").commit();
    }

    @Override
    public boolean registerRxBus() {
        return true;
    }
    @RxSubscribe(observeOnThread = EventThread.MAIN)
    public void listenRxIntegerEvent(String code) {
        String text = String.format("{ Receive event: %s\nCurrent thread: %s }", code, Thread.currentThread());
        Log.d("RxBus",text);
        textView.setText(code);
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public String toolbarTitle() {
        return "ceshi";
    }

    @Override
    public void showKeyWord(List<HotKeyWord> hotKeyWordList) {
        for (HotKeyWord hotKeyWord : hotKeyWordList) {
            Log.i(TAG, "showKeyWord: " + hotKeyWord.getKeyWordText());
            textView.setText(hotKeyWord.getKeyWordText());
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        Log.i(TAG, "showMessage: " + message);
    }
}

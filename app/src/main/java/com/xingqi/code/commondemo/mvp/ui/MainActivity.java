package com.xingqi.code.commondemo.mvp.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xingqi.code.commondemo.R;
import com.xingqi.code.commondemo.mvp.contract.HotKeyWordContract;
import com.xingqi.code.commondemo.mvp.model.HotKeyWordModel;
import com.xingqi.code.commondemo.mvp.model.entity.HotKeyWord;
import com.xingqi.code.commondemo.mvp.presenter.HotKeyWordPresenter;
import com.xingqi.code.commonlib.base.BaseActivity;
import com.xingqi.code.commonlib.entity.EventMessage;
import com.xingqi.code.commonlib.manager.LoadingDialogManager;
import com.xingqi.code.commonlib.rx.ResponseException;
import com.xingqi.code.commonlib.rx.RxErrorHandler;
import com.xingqi.code.commonlib.utils.PermissionUtil;
import com.xingqi.code.commonlib.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HotKeyWordPresenter> implements HotKeyWordContract.View {
    private static final String TAG = "MainActivity";
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.container)
    FrameLayout container;

    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPermissions = new RxPermissions(this);
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                ToastUtil.toast(MainActivity.this,"请求成功");
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                ToastUtil.toast(MainActivity.this,"请求失败");
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                ToastUtil.toast(MainActivity.this,"不在询问");
            }
        }, rxPermissions, new RxErrorHandler() {
            @Override
            public ResponseException handleException(Throwable e) {
                return null;
            }
        });
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        String message = (String) event.getData();
        textView.setText(message);
    }

    @Override
    public boolean registerEventBus() {
        return true;
    }


    @Override
    protected HotKeyWordPresenter initPresenter() {
        return new HotKeyWordPresenter(new HotKeyWordModel(), this);
    }

    @Override
    public boolean displayNavigateIcon() {
        return false;
    }

    @Override
    public boolean isRootPage() {
        return true;
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
        Log.e(TAG, "showMessage: " + message);
    }
    @Override
    public  void showLoading() {

        LoadingDialogManager.getInstance().showLoading(getSupportFragmentManager());

    }

    @Override
    public  void hideLoading() {
        LoadingDialogManager.getInstance().hideLoading();
    }

}

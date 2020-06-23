package com.xingqi.code.commonlib.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.xingqi.code.commonlib.R;
import com.xingqi.code.commonlib.mvp.BasePresenter;
import com.xingqi.code.commonlib.swipeback.SwipeBackActivityHelper;
import com.xingqi.code.commonlib.swipeback.SwipeBackLayout;
import com.xingqi.code.commonlib.utils.CommonUtils;
import com.xingqi.code.commonlib.utils.ScreenUtil;
import com.xingqi.code.commonlib.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IActivity {
    protected P mPresenter;
    private SwipeBackActivityHelper mHelper;
    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        //设置侧滑返回
        setUpSwipeBack();
        IAppStyle iAppStyle = new DefaultAppStyleImpl.Builder(this)
                .setDarkStatusBarText(darkStatusBarText())
                .setDisplayNavigateIcon(displayNavigateIcon())
                .setHasToolbar(hasToolbar())
                .setNavigateIconRes(navigateIconRes())
                .setToolbarTitle(toolbarTitle())
                .setToolbarColor(toolbarColor())
                .setListener((v -> {onNavigateClick();}))
                .build();
        //设置状态栏
        iAppStyle.setAppBarStyle();
        mPresenter = initPresenter();
        initData();
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();

    }
    protected abstract P initPresenter();

    private CompositeDisposable mCompositeDisposable;

    protected void addDisposable(Disposable disposable) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
    @Override
    protected void onDestroy() {
        disposable();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
        release();
        super.onDestroy();
    }
    @Override
    public void disposable(){
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
    }
    @Override
    public Context getOwnContext(){
        return this;
    }
    @Override
    public int toolbarColor() {
        return R.color.colorPrimary;
    }

    @Override
    public boolean displayNavigateIcon() {
        return true;
    }

    @Override
    public int navigateIconRes() {
        return R.drawable.ic_fanhui;
    }

    @Override
    public boolean darkStatusBarText() {
        return false;
    }

    @Override
    public void onNavigateClick() {
        onBackPressed();
    }
    private boolean isSupportSwipeBack(){
        if(!isRootPage()){
            return true;
        }else{
            return false;
        }
    }
    private void setUpSwipeBack(){
        // 可以调用该方法，设置是否允许滑动退出
        SwipeBackLayout mSwipeBackLayout = mHelper.getSwipeBackLayout();
        mSwipeBackLayout.setEnableGesture(isSupportSwipeBack());
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(ScreenUtil.dip2px(this,30));
    }

    //返回键监听
    @Override
    public void onBackPressed() {
        if(isRootPage()){
            exitAfterTwice();
        }else{
            super.onBackPressed();
        }

    }
    private long exitTime = 0;
    private void exitAfterTwice(){
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.toast(this,"再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            CommonUtils.exitApp();
        }
    }
}

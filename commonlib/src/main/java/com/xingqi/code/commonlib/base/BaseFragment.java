package com.xingqi.code.commonlib.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xingqi.code.commonlib.R;
import com.xingqi.code.commonlib.mvp.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IFragment{
    protected P mPresenter;
    public static <T extends BaseFragment> T getInstance(Bundle bundle,Class<T> fragmentClass){
        try {
            T t = fragmentClass.newInstance();
            t.setArguments(bundle);
            return t;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CompositeDisposable mCompositeDisposable;

    protected void addDisposable(Disposable disposable) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = initPresenter();
        initData();

    }
    protected abstract P initPresenter();
    @Override
    public void onDestroy() {
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
        release();
        super.onDestroy();
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
        getActivity().onBackPressed();
    }
}

package com.xingqi.code.commondemo.mvp.ui;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.xingqi.code.commondemo.R;
import com.xingqi.code.commondemo.adapter.ComplexAdapter;
import com.xingqi.code.commondemo.complex.FooterOne;
import com.xingqi.code.commondemo.complex.HeaderOne;
import com.xingqi.code.commonlib.base.BaseActivity;
import com.xingqi.code.commonlib.complex.RecyclerComplex;
import com.xingqi.code.commonlib.mvp.BasePresenter;

import butterknife.BindView;

public class ComplexActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public boolean isRootPage() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_complex;
    }

    @Override
    public void release() {

    }

    @Override
    public void initData() {
        ComplexAdapter adapter = new ComplexAdapter(this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(adapter);
        RecyclerComplex.with(recyclerView)
                .addHeader(new HeaderOne(this))
                .addHeader(new HeaderOne(this))
                .addFooter(new FooterOne(this))
                .addFooter(new FooterOne(this))
                .addFooter(new FooterOne(this))
//                .setFullSpan(false)
                .build();
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
}

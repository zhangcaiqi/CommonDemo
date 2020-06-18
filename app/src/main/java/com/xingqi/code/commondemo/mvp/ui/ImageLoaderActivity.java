package com.xingqi.code.commondemo.mvp.ui;


import android.os.Bundle;
import android.widget.ImageView;

import com.xingqi.code.commondemo.R;
import com.xingqi.code.commonlib.base.BaseActivity;
import com.xingqi.code.commonlib.imageloader.ImageLoader;
import com.xingqi.code.commonlib.imageloader.glide.ImageConfigImpl;
import com.xingqi.code.commonlib.mvp.BasePresenter;

import butterknife.BindView;

public class ImageLoaderActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_loader;
    }

    @Override
    public boolean isRootPage() {
        return false;
    }

    @Override
    public void release() {

    }

    @Override
    public void initData() {
        ImageLoader.loadImage(this,
                ImageConfigImpl.builder()
                        .url("http://pic.sc.chinaz.com/files/pic/pic9/201911/zzpic20859.jpg")
                        .imageView(imageView)
                        .isCircle(true)
                        .build());
        ImageLoader.loadImage(this,
                ImageConfigImpl.builder()
                        .url("http://pic.sc.chinaz.com/files/pic/pic9/201907/zzpic19195.jpg")
                        .imageView(imageView)
                        .isCircle(true)
                        .build());
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

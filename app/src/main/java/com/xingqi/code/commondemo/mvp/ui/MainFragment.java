package com.xingqi.code.commondemo.mvp.ui;

import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.xingqi.code.commondemo.R;
import com.xingqi.code.commondemo.mvp.contract.HotKeyWordContract;
import com.xingqi.code.commondemo.mvp.model.HotKeyWordModel;
import com.xingqi.code.commondemo.mvp.model.entity.HotKeyWord;
import com.xingqi.code.commondemo.mvp.presenter.HotKeyWordPresenter;
import com.xingqi.code.commonlib.base.BaseFragment;
import com.xingqi.code.commonlib.entity.EventMessage;
import com.xingqi.code.commonlib.manager.LoadingDialogManager;
import com.xingqi.code.commonlib.utils.CommonUtils;
import com.xingqi.code.commonlib.utils.EventBusUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment<HotKeyWordPresenter> implements HotKeyWordContract.View {
    @BindView(R.id.send_msg_btn)
    Button sendMsgBtn;
    @BindView(R.id.jump_to_page)
    Button jumpToPage;
    @BindView(R.id.jump_to_complex)
    Button jumpToComplex;
    @BindView(R.id.jump_to_image)
    Button jumpToImage;
    @BindView(R.id.jump_to_indicator)
    Button jumpToIndicator;

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
    public boolean hasToolbar() {
        return false;
    }

    @Override
    public String toolbarTitle() {
        return null;
    }

    @OnClick(R.id.send_msg_btn)
    public void onViewClicked() {
        EventBusUtil.post(new EventMessage(2,"sddfsdf"));
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

    private static final String TAG = "MainFragment";
    @Override
    public void showMessage(@NonNull String message) {
        Log.e(TAG, "showMessage: "+message);
    }

    @Override
    public  void showLoading() {

        LoadingDialogManager.getInstance().showLoading(getActivity().getSupportFragmentManager()                                                                                                                                                                                                                                                                                                                                                                                                                      );

    }

    @Override
    public  void hideLoading() {
        LoadingDialogManager.getInstance().hideLoading();
    }


    @OnClick(R.id.jump_to_page)
    public void jumpToPage() {
        CommonUtils.startActivity(RecyclePaginateActivity.class);
    }

    @OnClick(R.id.jump_to_complex)
    public void jumpToComplex() {
        CommonUtils.startActivity(ComplexActivity.class);
    }

    @OnClick(R.id.jump_to_image)
    public void jumpToImage() {
        CommonUtils.startActivity(ImageLoaderActivity.class);
        EventBusUtil.postSticky(new EventMessage(1,"sticky"));
    }

    @OnClick(R.id.jump_to_indicator)
    public void jumpToIndicator() {
        CommonUtils.startActivity(IndicatorActivity.class);
    }
}

package com.xingqi.code.commonlib.delegate;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.threshold.rxbus2.RxBus;
import com.xingqi.code.commonlib.base.DefaultAppStyleImpl;
import com.xingqi.code.commonlib.base.BaseApplication;
import com.xingqi.code.commonlib.base.IActivity;
import com.xingqi.code.commonlib.base.IAppStyle;
import com.xingqi.code.commonlib.swipeback.SwipeBackActivityHelper;
import com.xingqi.code.commonlib.swipeback.SwipeBackLayout;
import com.xingqi.code.commonlib.utils.AppManager;
import com.xingqi.code.commonlib.utils.ScreenUtil;

import java.util.List;


public class ActivityDelegateImpl implements ActivityDelegate{

    private Activity activity;
    private IActivity iActivity;


    public ActivityDelegateImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(isAppSelfActivity()){
            iActivity = (IActivity) activity;
        }
        AppManager.getInstance().addActivity(activity);
        if(iActivity.registerRxBus()){
            RxBus.getDefault().register(activity);
        }
        //放置最后，框架外部的扩展回调
        List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycleCallbackList
                = BaseApplication.fragmentLifecycleCallbacksList;
        for(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks:fragmentLifecycleCallbackList){
            ((AppCompatActivity)activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks,true);
        }
    }




    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {
//        List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycleCallbackList
//                = BaseApplication.fragmentLifecycleCallbacksList;
//        for(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks:fragmentLifecycleCallbackList){
//            ((AppCompatActivity)activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks);
//        }
        if(iActivity.registerRxBus()){
            RxBus.getDefault().unregister(this);
        }
        AppManager.getInstance().finishActivity(activity);
    }

    private boolean isAppSelfActivity(){
        if(activity instanceof IActivity){
            return true;
        }
        return false;
    }
}

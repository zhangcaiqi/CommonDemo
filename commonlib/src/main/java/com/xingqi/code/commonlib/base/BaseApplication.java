package com.xingqi.code.commonlib.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


import androidx.fragment.app.FragmentManager;

import com.threshold.rxbus2.RxBus;
import com.xingqi.code.commonlib.delegate.ActivityDelegate;
import com.xingqi.code.commonlib.delegate.FragmentDelegate;
import com.xingqi.code.commonlib.lifecycle.SysActivityLifecycleCallback;
import com.xingqi.code.commonlib.lifecycle.SysFragmentLifecycleCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;

public abstract class BaseApplication extends Application {
    private static Context context;
    private static Handler handler;
    public static List<ActivityLifecycleCallbacks> activityLifecycleCallbacksList = new ArrayList<>();
    public static List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycleCallbacksList = new ArrayList<>();

    public final static Map<Integer, ActivityDelegate> activityDelegateMap = new HashMap<>();
    public final static Map<Integer, FragmentDelegate> fragmentDelegateMap = new HashMap<>();
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        activityLifecycleCallbacksList.add(new SysActivityLifecycleCallback());
        fragmentLifecycleCallbacksList.add(new SysFragmentLifecycleCallback());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        //在Android中使用注解（RxSubscribe）在主线程（UI线程）上处理订阅的事件
        RxBus.setMainScheduler(AndroidSchedulers.mainThread());
        for(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks:activityLifecycleCallbacksList){
            registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks:activityLifecycleCallbacksList){
            unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    public static Context getContext(){
        return context;
    }

    public static Handler getHandler(){
        return handler;
    }
    protected abstract void addActivityCallback(List<ActivityLifecycleCallbacks> activityLifecycleCallbacksList);

    protected abstract void addFragmentCallback(List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycleCallbacksList);


}

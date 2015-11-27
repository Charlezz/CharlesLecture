package com.kangjungu.www.fourthapp.preference;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Charles on 15. 11. 22..
 */
public class MyApplication extends Application {


    public static final String TAG = "MyApplication";

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //애플리케이션 최초 실행될때 호출
        context = this;
        Log.e(TAG, "애플리케이션 onCreate");
    }
}

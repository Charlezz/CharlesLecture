package com.tistory.charlezz.alarm;

import android.app.Application;
import android.content.Context;

/**
 * Created by Charles on 16. 1. 10..
 */
public class MyApp extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}

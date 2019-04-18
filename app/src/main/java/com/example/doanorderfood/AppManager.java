package com.example.doanorderfood;

import android.app.Application;

public class AppManager extends Application {
    public static final String TAG = "AppManager";

    private static AppManager sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static synchronized AppManager getInstance() {
        return sInstance;
    }
}
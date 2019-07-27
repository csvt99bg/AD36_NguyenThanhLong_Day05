package com.example.ad36_nguyenthanhlong_day05;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

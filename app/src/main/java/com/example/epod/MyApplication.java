package com.example.epod;

import android.app.Application;

import com.example.epod.application.AppComponent;

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        appComponent = DaggerAppComponent
    }
}

package com.example.prm392_finalproject.Session;

import android.app.Application;

public class UserApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UserDataManager.init(getApplicationContext());
    }
}

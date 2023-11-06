package com.example.prm392_finalproject.Session;

import android.content.Context;

public class UserDataManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static  UserDataManager instance;
    private UserSharedPreference userSharedPreference;

    public static void init(Context context) {
        instance = new UserDataManager();
        instance.userSharedPreference = new UserSharedPreference(context);
    }

    public static UserDataManager getInstance(){
        if (instance == null) {
            instance = new UserDataManager();
        }
        return instance;
    }

}

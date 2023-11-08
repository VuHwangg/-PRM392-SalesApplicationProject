package com.example.prm392_finalproject.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPreference {
    private static final String USER_SHARED_PREFERENCE = "USER_SHARED_PREFERENCE";
    private Context context;

    public UserSharedPreference(Context context) {
        this.context = context;
    }


    public void putStringValue(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_SHARED_PREFERENCE
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_SHARED_PREFERENCE
                , Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public void removeValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_SHARED_PREFERENCE
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
    }

    public void putBooleanValue(String key, boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_SHARED_PREFERENCE
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_SHARED_PREFERENCE
                , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, true);
    }
}

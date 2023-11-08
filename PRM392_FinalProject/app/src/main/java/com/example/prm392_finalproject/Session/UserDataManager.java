package com.example.prm392_finalproject.Session;

import android.content.Context;

import com.example.prm392_finalproject.DTOModels.User_Login_DTO_Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDataManager {
    public static final String USER = "USER";
    public static final String NOTIFY = "NOTIFY";
    private static Gson gson;
    private static  UserDataManager instance;
    private UserSharedPreference userSharedPreference;

    public static void init(Context context) {
        instance = new UserDataManager();
        instance.userSharedPreference = new UserSharedPreference(context);
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public static UserDataManager getInstance(){
        if (instance == null) {
            instance = new UserDataManager();
        }
        return instance;
    }

    public static void setUserJsonString(User_Login_DTO_Response userLoginDtoResponse) {
        String json = gson.toJson(userLoginDtoResponse);
        UserDataManager.getInstance().userSharedPreference.putStringValue(USER, json);
    }

    public static User_Login_DTO_Response getUserPreference() {
        String json = UserDataManager.getInstance().userSharedPreference.getStringValue(USER);
        if (json.isEmpty()) return null;
        User_Login_DTO_Response userLoginDtoResponse = gson.fromJson(json, User_Login_DTO_Response.class);
        return userLoginDtoResponse;
    }

    public static void setNotify(boolean isNotify){
        UserDataManager.getInstance().userSharedPreference.putBooleanValue(NOTIFY,isNotify);
    }

    public static boolean getNotify(){
        return UserDataManager.getInstance().userSharedPreference.getBooleanValue(NOTIFY);
    }

}

package com.example.scindapsus.service.shared;

import android.content.SharedPreferences;

/**
 * Created by ej on 3/15/2017.
 */

public class SharedServiceImpl implements SharedService{

    private final SharedPreferences sharedPreferences;
    private final String TOKEN_NAME = "token";
    private final String USER_NAME = "username";

    public SharedServiceImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_NAME, token);
        editor.commit();
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(TOKEN_NAME,"");
    }

    @Override
    public void saveUserName(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, username);
        editor.commit();
    }

    @Override
    public String getUserName() {
        return sharedPreferences.getString(USER_NAME,"");
    }

}

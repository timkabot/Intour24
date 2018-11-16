package com.innopolis.intour24.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.view.Impl.MainActivity;

/**
 * Created by ekaterina on 6/20/17.
 */

public class SaveUser {
    public static final String APP_PREFERENCES = "usersave";
    public static final String APP_PREF_PHONE = "PHONE";
    public static final String APP_PREF_NAME = "NAME";
    public static final String APP_PREF_ID = "USER_ID";

    private static final String TAG = "SUDebug";

    public static void saveUser(Context context){
        User user = User.getInstance();
        Log.d(TAG, "Save user");
        SharedPreferences mSharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(APP_PREF_NAME, user.getName());
        editor.putString(APP_PREF_PHONE, user.getPhone());
        editor.putInt(APP_PREF_ID, user.getId());
        editor.apply();
    }


    public static void readUser(Context context){
        User user = User.getInstance();

        SharedPreferences mSharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(mSharedPreferences.contains(APP_PREF_NAME)) {
            user.setName(mSharedPreferences.getString(APP_PREF_NAME, "no user"));
            Log.d(TAG, "Read user");
        }
        if(mSharedPreferences.contains(APP_PREF_PHONE)) {
            user.setPhone(mSharedPreferences.getString(APP_PREF_PHONE, "0000000000"));
            Log.d(TAG, "Read phone");
        }

        if(mSharedPreferences.contains(APP_PREF_ID)){
            user.setId(mSharedPreferences.getInt(APP_PREF_ID, -1));//-1 if no such user
            Log.d(TAG, "Read ID");
        }
    }
    public static void deleteUser(Context context)
    {
        User user = User.getInstance();
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit().clear().apply();
        user.setId(0);
        user.setPhone(null);
        user.setName(null);
        SaveUser.saveUser(context);
        System.out.println("After deleting  " + user.getPhone());
        user = User.getInstance();
    }



}

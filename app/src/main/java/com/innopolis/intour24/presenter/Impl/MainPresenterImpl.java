package com.innopolis.intour24.presenter.Impl;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.presenter.MainPresenter;
import com.innopolis.intour24.view.MainView;

/**
 * Created by Sergey Pinkevich on 03.06.2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    public void loadUserData() {
        SharedPreferences pref = ((AppCompatActivity)view).getApplicationContext()
                .getSharedPreferences("Preferences", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        setupUser(pref);
    }

    private void setupUser(SharedPreferences pref) {
        User user = User.getInstance();
        int id = pref.getInt(User.ID, 0);
        String name = pref.getString(User.NAME, null);
        String phone = pref.getString(User.PHONE, null);
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
    }
}

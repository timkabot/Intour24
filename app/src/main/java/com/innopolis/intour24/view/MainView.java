package com.innopolis.intour24.view;


import android.support.v4.app.Fragment;

/**
 * Created by Sergey Pinkevich on 03.06.2017.
 */

public interface MainView {
    void showNewFragment(Fragment fragment);
    void showFragment(Fragment fragment);
    void hideFragment(Fragment fragment);
}

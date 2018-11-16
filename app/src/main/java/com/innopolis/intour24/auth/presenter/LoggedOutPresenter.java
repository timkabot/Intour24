package com.innopolis.intour24.auth.presenter;

import android.view.View;

import com.innopolis.intour24.auth.view.LoggedOutView;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface LoggedOutPresenter {
    void getLoggedOutListener(View view);
    void setLoggedOutView(LoggedOutView loggedOutView);
}

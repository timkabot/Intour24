package com.innopolis.intour24.auth.presenter.Impl;

import android.view.View;

import com.innopolis.intour24.auth.presenter.LoggedOutPresenter;
import com.innopolis.intour24.auth.view.LoggedOutView;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public class LoggedOutPresenterImpl implements LoggedOutPresenter {

    private LoggedOutView view;

    @Override
    public void getLoggedOutListener(View view) {
        this.view.login();
    }

    @Override
    public void setLoggedOutView(LoggedOutView loggedOutView) {
        this.view = loggedOutView;
    }
}

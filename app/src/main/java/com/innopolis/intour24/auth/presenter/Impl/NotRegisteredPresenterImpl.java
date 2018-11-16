package com.innopolis.intour24.auth.presenter.Impl;

import android.view.View;

import com.innopolis.intour24.auth.presenter.NotRegisteredPresenter;
import com.innopolis.intour24.auth.view.NotRegisteredView;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public class NotRegisteredPresenterImpl implements NotRegisteredPresenter {

    private NotRegisteredView notRegisteredView;

    @Override
    public void getSignInListener(View view){
        notRegisteredView.startSignIn();
    }

    @Override
    public void getSignUpListener(View view){
        notRegisteredView.startSignUp();
    }

    @Override
    public void setButtonsView(NotRegisteredView buttonsView) {
        this.notRegisteredView = buttonsView;
    }
}

package com.innopolis.intour24.auth.presenter;

import android.view.View;

import com.innopolis.intour24.auth.view.NotRegisteredView;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface NotRegisteredPresenter {
    void getSignInListener(View view);
    void getSignUpListener(View view);
    void setButtonsView(NotRegisteredView buttonsView);
}

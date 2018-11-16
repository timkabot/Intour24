package com.innopolis.intour24.auth.presenter;

import android.view.View;

import com.innopolis.intour24.auth.view.AlreadyRegisteredView;
import com.innopolis.intour24.auth.view.Impl.LoginAlreadyRegisteredFragment;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface AlreadyRegisteredPresenter {
    void getScreenListener(View view);
    void setAlreadyRegisteredView(LoginAlreadyRegisteredFragment alreadyRegisteredView);
    void onLogOut();
    void onChangeName();
}

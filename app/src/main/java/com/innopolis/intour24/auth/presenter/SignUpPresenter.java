package com.innopolis.intour24.auth.presenter;

import android.view.View;

import com.innopolis.intour24.auth.view.SignUpView;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface SignUpPresenter {
    void getRegisterListener(View view);
    void setSignUpView(SignUpView signUpView);
    void saveUserToDatabase(String name, String phone);
}

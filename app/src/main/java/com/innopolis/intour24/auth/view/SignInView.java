package com.innopolis.intour24.auth.view;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface SignInView {
    void startAcceptPhoneNumber(String code);
    void startSignUpScreen();
    void showErrorNumber();

    void onNetworkError();
}

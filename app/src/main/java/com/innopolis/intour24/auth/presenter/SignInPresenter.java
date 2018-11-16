package com.innopolis.intour24.auth.presenter;

import android.view.View;

import com.innopolis.intour24.auth.view.Impl.SignInFragment;
import com.innopolis.intour24.auth.view.SignInView;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface SignInPresenter {
    void getLoginListener(View view);
    void setSignInView(SignInFragment signInView);
    void checkNumberInDatabase(String phone);
}

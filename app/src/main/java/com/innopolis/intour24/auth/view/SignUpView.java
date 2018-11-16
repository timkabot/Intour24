package com.innopolis.intour24.auth.view;

import android.content.Context;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface SignUpView {
    void showError();
    void startAcceptPhoneNumber(String code);

    void emptyName();

    void wrongPhone();

    Context getAppContext();

}

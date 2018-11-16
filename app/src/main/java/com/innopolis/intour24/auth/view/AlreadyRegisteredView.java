package com.innopolis.intour24.auth.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public interface AlreadyRegisteredView {
    void changeUser();
    void logout();
    Context getmContext();
    FragmentActivity getmActivity();
}

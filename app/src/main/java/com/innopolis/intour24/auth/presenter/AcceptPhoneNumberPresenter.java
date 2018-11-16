package com.innopolis.intour24.auth.presenter;

import com.innopolis.intour24.auth.view.AcceptPhoneNumberView;

/**
 * Created by Sergey Pinkevich on 16.06.2017.
 */

public interface AcceptPhoneNumberPresenter {
    void setSignUpView(AcceptPhoneNumberView acceptPhoneNumberView);
    void compareCode(String codeFromNetwork, String codeFromEditText);
}

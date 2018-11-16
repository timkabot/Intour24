package com.innopolis.intour24.auth.presenter.Impl;

import com.innopolis.intour24.auth.presenter.AcceptPhoneNumberPresenter;
import com.innopolis.intour24.auth.view.AcceptPhoneNumberView;

/**
 * Created by Sergey Pinkevich on 16.06.2017.
 */

public class AcceptPhoneNumberPresenterImpl implements AcceptPhoneNumberPresenter {

    private AcceptPhoneNumberView view;


    @Override
    public void setSignUpView(AcceptPhoneNumberView acceptPhoneNumberView) {
        view = acceptPhoneNumberView;
    }

    @Override
    public void compareCode(String codeFromNetwork, String codeFromEditText) {
        if (codeFromNetwork.equals(codeFromEditText)) {
            view.setSuccessMessage(); view.hideKeyboard();
        }
        else
            view.setErrorMessage();


    }
}

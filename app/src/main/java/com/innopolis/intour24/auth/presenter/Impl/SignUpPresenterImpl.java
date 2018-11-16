package com.innopolis.intour24.auth.presenter.Impl;

import android.view.View;

import com.innopolis.intour24.App;
import com.innopolis.intour24.PhoneChecker;
import com.innopolis.intour24.auth.presenter.SignUpPresenter;
import com.innopolis.intour24.auth.view.SignUpView;
import com.innopolis.intour24.model.RegisterResponse;
import com.innopolis.intour24.model.entity.User;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView signUpView;

    @Override
    public void getRegisterListener(View view) {
    }
    @Override

    public void setSignUpView(SignUpView signUpView){
        this.signUpView = signUpView;
    }

    @Override
    public void saveUserToDatabase(String name, String phone) {
        User user = User.getInstance();
        user.setName(name);
        user.setPhone(phone);

        if(!checkFormat(name, phone)) {
            return;
        }

        Observable<RegisterResponse> registerObservable = App.getAPI().registration(name, phone);
        registerObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseObject ->
                {
                    user.setId(responseObject.getId());
                    determineStatus(responseObject);
                });

    }

    /**
     * @param name to check
     * @param phone to check
     * @return true if everything is ok
     */
    private boolean checkFormat(String name, String phone) {
        boolean flag = true;
        if(!checkNameFormat(name)) {
            nullNameError();
            flag = false;
        }
        if(!checkPhoneFormat(phone)) {
            wrongPhoneError();
            flag = false;
        }
        return flag;
    }

    private void wrongPhoneError() {
        signUpView.wrongPhone();
    }

    private boolean checkPhoneFormat(String phone) {
        return PhoneChecker.checkPhone(phone);
    }


    private void determineStatus(RegisterResponse response) {
        if (response.getStatus().equals("OK")) {
            signUpView.startAcceptPhoneNumber(response.getCode());
        }else if (response.getStatus().equals("ERROR"))
            signUpView.showError();
    }

    /**
     * TODO for some reason it doesn't equal to "" even when it ""
     * @param name
     * @return
     */
    private boolean checkNameFormat(String name){
        if(name == null){
            return false;
        }else if (name.equals("")){
            return false;
        }

        return true;
    }

    private void nullNameError(){
        signUpView.emptyName();
    }
}

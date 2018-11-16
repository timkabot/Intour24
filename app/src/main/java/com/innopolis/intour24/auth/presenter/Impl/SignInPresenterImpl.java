package com.innopolis.intour24.auth.presenter.Impl;

import android.view.View;

import com.innopolis.intour24.App;
import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.presenter.SignInPresenter;
import com.innopolis.intour24.auth.view.Impl.SignInFragment;
import com.innopolis.intour24.model.LoginResponse;
import com.innopolis.intour24.model.entity.User;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public class SignInPresenterImpl implements SignInPresenter {

    private SignInFragment signInView;

    private final static String TAG = "SIPIDebug";

    @Override
    public void getLoginListener(View view){

    }

    @Override
    public void setSignInView(SignInFragment signInView){
        this.signInView = signInView;
    }

    @Override
    public  void checkNumberInDatabase(String phone) {
        if (!App.checkNumberFormat(phone,signInView.getString(R.string.wrong_phone),signInView.getContext())){
            return;
        }
        phone = App.transformPhone(phone);
        Observable<LoginResponse> statusObservable = App.getAPI().checkPhone(phone);
        statusObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError);

    }

    private void onNext(LoginResponse loginResponse) {
        determineStatus(loginResponse);
    }

    private void onError(Throwable throwable) {
    }

    /**
     * Если пользователь действительно был зарегистрирован, то поле 'registered' = 1
     * Если нет, то 0. В этом случае он перенаправляется на экран регистрации
     * @param response
     */
    private void determineStatus(LoginResponse response) {
        User user = User.getInstance();
        user.setId(response.getId());
        System.out.println("Code is " + response.getCode());
        if (response.getIsRegistered() == 1)

            signInView.startAcceptPhoneNumber(response.getCode());
        else
            signInView.showErrorNumber();
    }
}

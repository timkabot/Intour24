package com.innopolis.intour24.auth.presenter.Impl;

import android.content.Intent;
import android.view.View;

import com.innopolis.intour24.auth.presenter.AlreadyRegisteredPresenter;
import com.innopolis.intour24.auth.view.AlreadyRegisteredView;
import com.innopolis.intour24.auth.view.Impl.LoginAlreadyRegisteredFragment;
import com.innopolis.intour24.model.Constants;
import com.innopolis.intour24.model.SaveUser;
import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.view.Impl.MainActivity;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public class AlreadyRegisteredPresenterImpl implements AlreadyRegisteredPresenter {

    private LoginAlreadyRegisteredFragment view;

    public AlreadyRegisteredPresenterImpl(LoginAlreadyRegisteredFragment view) {
        this.view = view;
    }

    @Override
    public void getScreenListener(View view) {

    }

    @Override
    public void setAlreadyRegisteredView(LoginAlreadyRegisteredFragment alreadyRegisteredView) {
        this.view = alreadyRegisteredView;
    }

    @Override
    public void onLogOut() {
        SaveUser.deleteUser(view.getActivity().getApplicationContext());
        Intent i = new Intent(view.getActivity(), MainActivity.class);
        i.putExtra(Constants.SCREEN_INTENT,Constants.LOGIN_SCREEN);
        view.startActivity(i);
    }

    @Override
    public void onChangeName() {
        //TODO change name
        User user = User.getInstance();
    }

    public AlreadyRegisteredView getView() {
        return view;
    }
}

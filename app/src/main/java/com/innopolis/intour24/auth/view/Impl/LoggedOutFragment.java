package com.innopolis.intour24.auth.view.Impl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.presenter.Impl.LoggedOutPresenterImpl;
import com.innopolis.intour24.auth.presenter.Impl.SignInPresenterImpl;
import com.innopolis.intour24.auth.presenter.LoggedOutPresenter;
import com.innopolis.intour24.auth.presenter.SignInPresenter;
import com.innopolis.intour24.auth.view.LoggedOutView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoggedOutFragment extends Fragment implements LoggedOutView {

    @BindView(R.id.login_button) Button loginButton;

    private LoggedOutPresenter presenter = new LoggedOutPresenterImpl();

    public LoggedOutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logged_out, container, false);
        ButterKnife.bind(this, view);

        presenter.setLoggedOutView(this);
        loginButton.setOnClickListener(presenter::getLoggedOutListener);

        return view;
    }

    @Override
    public void login() {

    }
}

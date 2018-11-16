package com.innopolis.intour24.auth.view.Impl;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.presenter.AlreadyRegisteredPresenter;
import com.innopolis.intour24.auth.presenter.Impl.AlreadyRegisteredPresenterImpl;
import com.innopolis.intour24.auth.view.AlreadyRegisteredView;
import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.view.Impl.MainActivity;

import org.apache.log4j.chainsaw.Main;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginAlreadyRegisteredFragment extends Fragment implements AlreadyRegisteredView {

    @BindView(R.id.phone_text) TextView phoneText;
    @BindView(R.id.user_text) TextView userText;
    @BindView(R.id.logout_button) Button logoutButton;

    private AlreadyRegisteredPresenter presenter;

    public LoginAlreadyRegisteredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_already_registered, container, false);
        ButterKnife.bind(this, view);

        initToolbar();

        User user = User.getInstance();
        presenter = new AlreadyRegisteredPresenterImpl(this);

        phoneText.setText("+" + user.getPhone());

        String name = "";
        if(user.getName() != null)
            name = ", " + user.getName();

        userText.setText(getString(R.string.welcome)  + name);

        logoutButton.setOnClickListener(v-> presenter.onLogOut());
        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.cabinet_toolbar));
    }

    public FragmentActivity getmActivity()
    {
        return getActivity();
    }
    @Override
    public void changeUser() {

    }

    @Override
    public void logout() {

    }

    @Override
    public Context getmContext() {
        return getContext();
    }

}

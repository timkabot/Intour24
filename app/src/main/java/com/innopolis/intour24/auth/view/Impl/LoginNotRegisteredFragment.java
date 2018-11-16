package com.innopolis.intour24.auth.view.Impl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.presenter.Impl.NotRegisteredPresenterImpl;
import com.innopolis.intour24.auth.presenter.NotRegisteredPresenter;
import com.innopolis.intour24.auth.view.NotRegisteredView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginNotRegisteredFragment extends Fragment implements NotRegisteredView {

    @BindView(R.id.register_button) Button registerButton;
    @BindView(R.id.login_button) Button loginButton;

    private final static String TAG = "LNRFDebug";

    private NotRegisteredPresenter presenter = new NotRegisteredPresenterImpl();

    public LoginNotRegisteredFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_not_registered, container, false);
        ButterKnife.bind(this, view);

        Log.d(TAG, "LoginNotRegisteredFragment created");

        initToolbar();

        presenter.setButtonsView(this);
        loginButton.setOnClickListener(presenter::getSignInListener);
        registerButton.setOnClickListener(presenter::getSignUpListener);

        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.cabinet_toolbar));
    }

    @Override
    public void startSignIn() {
        FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new SignInFragment())
                .addToBackStack(null)
                .commit();

        Log.d(TAG, "Start sign in");
    }

    @Override
    public void startSignUp() {
        FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new SignUpFragment())
                .addToBackStack(null)
                .commit();
        Log.d(TAG, "Start sign up");
    }
}

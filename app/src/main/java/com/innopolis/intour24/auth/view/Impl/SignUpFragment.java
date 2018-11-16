package com.innopolis.intour24.auth.view.Impl;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.presenter.Impl.SignUpPresenterImpl;
import com.innopolis.intour24.auth.presenter.SignUpPresenter;
import com.innopolis.intour24.auth.view.SignUpView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements SignUpView {

    @BindView(R.id.name) EditText nameText;
    @BindView(R.id.phone_number) EditText phoneNumber;
    @BindView(R.id.register_button) Button registerButton;

    private SignUpPresenter presenter = new SignUpPresenterImpl();

    private static final String TAG = "SUFDebug";

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);

        Log.d(TAG, "Sign up fragment created");

        initToolbar();

        presenter.setSignUpView(this);
        registerButton.setOnClickListener(p -> presenter.saveUserToDatabase(nameText.getText().toString(),
                phoneNumber.getText().toString()));

        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.sign_up_toolbar));
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.repeat_account), Toast.LENGTH_LONG).show();
    }

    @Override
    public void startAcceptPhoneNumber(String code) {
        FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, passDataToFragment(code))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void emptyName() {
        if(Build.VERSION.SDK_INT > 23)
            nameText.setBackgroundColor(getResources().getColor(R.color.wrong_format, this.getActivity().getTheme()));

        Log.d(TAG, "Wrong name");
    }

    @Override
    public void wrongPhone() {
        if(Build.VERSION.SDK_INT > 23)
            phoneNumber.setBackgroundColor(getResources().getColor(R.color.wrong_format, this.getActivity().getTheme()));

        Log.d(TAG, "Wrong phone");
    }

    @Override
    public Context getAppContext() {
        return this.getContext();
    }

    /**
     * Передача номера телефона, для отображения в тулбаре
     * @return
     */
    private AcceptPhoneNumberFragment passDataToFragment(String code) {
        AcceptPhoneNumberFragment fragment = new AcceptPhoneNumberFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AcceptPhoneNumberFragment.CODE, code);
        bundle.putString(AcceptPhoneNumberFragment.NUMBER_TEXT, phoneNumber.getText().toString());
        fragment.setArguments(bundle);
        return fragment;
    }
}

package com.innopolis.intour24.auth.view.Impl;


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

import com.innopolis.intour24.NetworkError;
import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.presenter.Impl.SignInPresenterImpl;
import com.innopolis.intour24.auth.presenter.SignInPresenter;
import com.innopolis.intour24.auth.view.SignInView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements SignInView {

    @BindView(R.id.phone_number) EditText phoneNumber;
    @BindView(R.id.login_button) Button loginButton;

    private static final String TAG = "SIFDebug";

    private SignInPresenter presenter = new SignInPresenterImpl();

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);

        Log.d(TAG, "SignInFragment created");

        initToolbar();

        presenter.setSignInView(this);
        loginButton.setOnClickListener(p ->
                presenter.checkNumberInDatabase(phoneNumber.getText().toString()));

        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.sign_in_toolbar));
    }

    /**
     * Если пользователь есть в базе, показываем экран ввода кода
     * @param code
     */
    @Override
    public void startAcceptPhoneNumber(String code) {
        FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, passDataToFragment(code))
                .addToBackStack(null)
                .commit();
    }

    /**
     * Если пользователя с таким номером не существует - редирект на экран ренистрации
     */
    @Override
    public void startSignUpScreen() {
        FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new SignUpFragment())
                .addToBackStack(null)
                .commit();
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

    /**
     * Toast, информирующий, что пользователя не существует
     */
    @Override
    public void showErrorNumber() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.wrong_account), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNetworkError() {
        NetworkError.onNetworkAccured(this.getActivity());
    }
}

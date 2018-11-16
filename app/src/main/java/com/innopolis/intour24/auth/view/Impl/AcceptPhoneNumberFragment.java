package com.innopolis.intour24.auth.view.Impl;


import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.presenter.AcceptPhoneNumberPresenter;
import com.innopolis.intour24.auth.presenter.Impl.AcceptPhoneNumberPresenterImpl;
import com.innopolis.intour24.auth.view.AcceptPhoneNumberView;
import com.innopolis.intour24.model.SaveUser;
import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.presenter.Impl.BookingPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptPhoneNumberFragment extends Fragment implements AcceptPhoneNumberView {

    @BindView(R.id.code_text) EditText codeText;

    public static final String CODE = "code";
    public static final String NUMBER_TEXT = "number_text";

    private AcceptPhoneNumberPresenter presenter;
    private Bundle bundle;

    public AcceptPhoneNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accept_phone_number, container, false);
        ButterKnife.bind(this, view);

        initToolbar();

        presenter = new AcceptPhoneNumberPresenterImpl();
        presenter.setSignUpView(this);
        bundle = this.getArguments();

        setupTextView();


        return view;
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(codeText, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromInputMethod(codeText.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
    }

    private void setupTextView() {
        codeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 6 && bundle != null)
                    presenter.compareCode(bundle.getString(CODE), s.toString());
            }
        });


        showKeyboard();
    }

    private void initToolbar() {
        if (bundle != null)
            ((AppCompatActivity)getActivity()).getSupportActionBar()
                    .setTitle(getString(R.string.accept_number) + " " + bundle.getString(NUMBER_TEXT));
    }

    @Override
    public void setSuccessMessage() {
        //TODO save user
        User user = User.getInstance();
        user.setPhone(bundle.getString(NUMBER_TEXT));
        SaveUser.saveUser(getContext());
        Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

        Log.d("Sequence", "Success code");

        if(BookingPresenterImpl.isFromBooking){//if we came from booking screen
            this.getActivity().finish();//finish this activity
        }else {//otherwise
            showSuccessAuth();//show success login fragment
        }
    }

    @Override
    public void setErrorMessage() {
        Toast.makeText(getActivity().getApplicationContext(), "Wrong code", Toast.LENGTH_SHORT).show();
    }

    private void showSuccessAuth(){
        FragmentTransaction transaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new LoginAlreadyRegisteredFragment())
                .addToBackStack(null)
                .commit();
    }
}
package com.innopolis.intour24.view.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.view.Impl.AcceptPhoneNumberFragment;
import com.innopolis.intour24.auth.view.Impl.SignUpFragment;
import com.innopolis.intour24.model.Constants;
import com.innopolis.intour24.presenter.Impl.MainPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ekaterina on 6/20/17.
 */

public class BookingPayActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_registration);

        int mode = this.getIntent().getIntExtra(Constants.EXTRA_MODE, Constants.SOME_ERROR);//get mode

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        openFragment(mode);
        // start point is catalog of sights
        //showNewFragment(new SignUpFragment());
    }

    private void openFragment(int mode) {
        switch (mode){
            case Constants.MODE_NEW_USER:
                showNewFragment(new SignUpFragment());
                break;
            case Constants.MODE_ALREADY_REGISTERED:
                showNewFragment(passCodeAcceptFragment(getCode(), getPhoneNumber()));
                break;
        }
    }

    private AcceptPhoneNumberFragment passCodeAcceptFragment(String code, String phoneNumber){
        AcceptPhoneNumberFragment fragment = new AcceptPhoneNumberFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AcceptPhoneNumberFragment.CODE, code);
        bundle.putString(AcceptPhoneNumberFragment.NUMBER_TEXT, phoneNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void showNewFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public String getPhoneNumber() {
        return this.getIntent().getStringExtra(Constants.NUMBER_TEXT);
    }

    public String getCode() {
        return this.getIntent().getStringExtra(Constants.CODE);
    }


    @Override
    public void onBackPressed() {

        this.finish();
    }

}

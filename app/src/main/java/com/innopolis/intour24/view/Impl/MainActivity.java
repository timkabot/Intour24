package com.innopolis.intour24.view.Impl;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.innopolis.intour24.R;
import com.innopolis.intour24.auth.view.Impl.LoginAlreadyRegisteredFragment;
import com.innopolis.intour24.auth.view.Impl.LoginNotRegisteredFragment;
import com.innopolis.intour24.model.Constants;
import com.innopolis.intour24.model.SaveUser;
import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.presenter.Impl.MainPresenterImpl;
import com.innopolis.intour24.presenter.MainPresenter;
import com.innopolis.intour24.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar)           Toolbar toolbar;
    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
    private MenuItem firstItem, secondItem, thirdItem;
    private MainPresenter presenter;

    public void showFragment(final Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment.isHidden())
            ft.show(fragment);
        ft.commit();
    }

    public void hideFragment(final Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!fragment.isHidden())
            ft.hide(fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // start point is catalog of sights
        setListenerToBottomMenu();
        initItems();
        if(getIntent() != null) {
            String strdata = getIntent().getExtras().getString(Constants.SCREEN_INTENT);
            assert strdata != null;
            switch (strdata) {
                case Constants.CATALOG_SREEN : showCatalogFragment();break;
                case Constants.EXCURSIONS_SCREEN : showExcursionFragment();break;
                case Constants.LOGIN_SCREEN : showLoginFragment(); break;
            }
        }
        SaveUser.readUser(this);
    }
    public void showCatalogFragment() {
        showNewFragment(new CatalogFragment());
        presenter = new MainPresenterImpl(this);
        presenter.loadUserData();
    }
    public void showExcursionFragment()
    {
        showNewFragment(new MyExcursionsFragment());
    }
    public void showLoginFragment()
    {
        showNewFragment(checkRegistration());
    }
    public void initItems() {
        firstItem = bottomNavigationView.getMenu().findItem(R.id.action_places);
        secondItem = bottomNavigationView.getMenu().findItem(R.id.action_excursions);
        thirdItem = bottomNavigationView.getMenu().findItem(R.id.action_cabinet);
    }

    public void setItemsEnabled() {
        firstItem.setEnabled(true);
        secondItem.setEnabled(true);
        thirdItem.setEnabled(true);
    }

    public void setListenerToBottomMenu() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item ->
        {
            setItemsEnabled();
            item.setEnabled(false);
            switch (item.getItemId()) {
                case R.id.action_places:
                    showNewFragment(new CatalogFragment());
                    break;
                case R.id.action_excursions:
                    showNewFragment(new MyExcursionsFragment());
                    break;
                case R.id.action_cabinet:
                    showNewFragment(checkRegistration());
                    break;
            }
            return true;
        });
    }

    private Fragment checkRegistration() {
        SaveUser.readUser(getBaseContext());
        User user = User.getInstance();

        if(user.getPhone() != null)
            return new LoginAlreadyRegisteredFragment();

        return new LoginNotRegisteredFragment();
    }

    @Override
    public void showNewFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Without it back button must be pressed twice, first to close fragment, second to close
     * activity
     */
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
package com.innopolis.intour24.view.Impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.innopolis.intour24.R;
import com.innopolis.intour24.model.Constants;
import com.innopolis.intour24.model.SaveUser;

import org.apache.log4j.chainsaw.Main;

/**
 * Created by Timkabor on 5/24/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        SaveUser.readUser(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.SCREEN_INTENT, Constants.CATALOG_SREEN);
        startActivity(intent);
        finish();
    }
}

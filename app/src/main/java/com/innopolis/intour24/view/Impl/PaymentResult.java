package com.innopolis.intour24.view.Impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.innopolis.intour24.R;

import butterknife.ButterKnife;

/**
 * Created by Timkabor on 6/26/2017.
 */

public class PaymentResult extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);
        ButterKnife.bind(this);
        Intent i = new Intent(this,MainActivity.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivityForResult(i,1);
    }
}

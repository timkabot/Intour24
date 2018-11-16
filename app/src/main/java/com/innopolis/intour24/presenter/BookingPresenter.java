package com.innopolis.intour24.presenter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.innopolis.intour24.model.entity.Group;

/**
 * Created by ekaterina on 6/14/17.
 */

public interface BookingPresenter {
    void getExcursionData();

    void onNext(Group group);

    void onError(Throwable throwable);

    void onConfirmButton(String number);

    void onAdultItemSelected(int selectedItemPosition);

    void onChildItemSelected(int selectedItemPosition);

    void onCardRB();
    void onCashRB();

    void onPayButton();
    void onConditionCheckBox();

    void onActivityStart();

}

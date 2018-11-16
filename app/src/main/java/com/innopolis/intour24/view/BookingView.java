package com.innopolis.intour24.view;

import android.content.Context;

import com.innopolis.intour24.model.entity.Category;
import com.innopolis.intour24.model.entity.GroupProperty;
import com.innopolis.intour24.model.entity.Operator;

import java.util.List;

/**
 * Created by ekaterina on 6/14/17.
 */

public interface BookingView {
    void proceedPayment(int amount,int bookingId);

    void setProperties(List<GroupProperty> properties);
    void setCompany(Operator operator);
    void setPersonalData(String name, String number);
    void setExcursionName(String name);
    void setCategory(Category category);
    void setTotalCost(int cost);
    void setButtonText(String text);
    void setAdultCost(int price);
    void setChildCost(int price);
    void setImage(String src);
    void onNetworkError();
    void setTimeDuration(String time, long duration);
    void onPay();
    void onConfirm();
    void showNewUserForm();
    void showOldUserForm();
    void setUpAdultSpinner(Integer number);
    void setUpChildSpinner(Integer number);
    void setLogo(String url);

    void deactivatePayButton();

    void activatePayButton();

    String getPhoneNumber();

    void setCardPayButton(int amount);
    void setCashPayButton();

    void hideKeyboard();
    void onBackButton();

    void noUserError();

    void startAcceptNumber(String code);

    void showExcursions();
}

package com.innopolis.intour24.view.Impl;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.innopolis.intour24.App;
import com.innopolis.intour24.NetworkError;
import com.innopolis.intour24.R;
import com.innopolis.intour24.model.Constants;
import com.innopolis.intour24.model.SaveUser;
import com.innopolis.intour24.model.entity.Category;
import com.innopolis.intour24.model.entity.GroupProperty;
import com.innopolis.intour24.model.entity.Operator;
import com.innopolis.intour24.presenter.BookingPresenter;
import com.innopolis.intour24.presenter.Impl.BookingPresenterImpl;
import com.innopolis.intour24.view.BookingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ekaterina on 5/30/17.
 */

public class BookingActivity extends TinkoffActivity implements BookingView {
    @BindView(R.id.excursionName)        TextView nameTextView;
    @BindView(R.id.excursionCategory)    TextView categoryTextView;
    @BindView(R.id.timeAndDuration)      TextView timeAndDurationTextView;
    @BindView(R.id.company)              TextView companyTextView;
    @BindView(R.id.personalInfo)         TextView personalInfo;
    @BindView(R.id.adultForEachPrice)    TextView adultEachPriceTextView;
    @BindView(R.id.childrenForEachPrice) TextView childEachPriceTextView;
    @BindView(R.id.totalCostHeading)     TextView totalCost;

    @BindView(R.id.adultSpinner)         Spinner adultSpinner;
    @BindView(R.id.childSpinner)         Spinner childSpinner;

    @BindView(R.id.confirmButton)        Button confirmButton;
    @BindView(R.id.payButton)            Button payButton;

    @BindView(R.id.newUserForm)          LinearLayout newUserForm;
    @BindView(R.id.propertiesList)       LinearLayout propertiesLinearLayout;

    @BindView(R.id.cardPayRB)            RadioButton cardPayRB;
    @BindView(R.id.cashPayRB)            RadioButton cashPayRB;

    @BindView(R.id.conditionCheckBox)    CheckBox conditionCheckBox;

    @BindView(R.id.phoneNumberET)        EditText phoneNumber;

    @BindView(R.id.logo)                 ImageView logo;
    @BindView(R.id.tourView)             ImageView tourView;
    @BindView(R.id.categoryLogoWhite)    ImageView busWhitelogo;

    private static final String TAG = "BPIDebug";

    BookingPresenter bookingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_screen);

        ButterKnife.bind(this);

        bookingPresenter = new BookingPresenterImpl(this);


        payButton.setOnClickListener(v -> onPay());
        confirmButton.setOnClickListener(v -> onConfirm());

        cardPayRB.setOnClickListener(v -> onCardRB());
        cashPayRB.setOnClickListener(v -> onCashRB());

        conditionCheckBox.setOnClickListener(v -> onConditionCheckBox());
        phoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        conditionCheckBox.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void setUpAdultSpinner(Integer number) {//Till that number
        List<String> days = new ArrayList<>();
        for (int i = 0; i <= number; i++)
            days.add(String.valueOf(i));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        adultSpinner.setAdapter(adapter);

        adultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                bookingPresenter.onAdultItemSelected(selectedItemPosition);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void setUpChildSpinner(Integer number) {//Till that number
        List<String> days = new ArrayList<>();
        for (int i = 0; i <= number; i++)
            days.add(String.valueOf(i));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, days);
        childSpinner.setAdapter(adapter);

        childSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                bookingPresenter.onChildItemSelected(selectedItemPosition);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void setLogo(String url) {
        App.loadImageFromURL(url,logo,this);
        //Picasso.with(this.getApplicationContext()).load(url).fit().centerCrop().into(logo);
    }

    @Override
    public void deactivatePayButton() {
        payButton.setActivated(false);
        Log.d(TAG, "Button was deactivated");
    }

    @Override
    public void activatePayButton() {
        payButton.setActivated(true);
        Log.d(TAG, "Button was activated");
    }

    @Override
    public String getPhoneNumber() {
        return String.valueOf(phoneNumber.getText());
    }

    @Override
    public void setCardPayButton(int amount) {
        payButton.setText(getString(R.string.pay_button_card) + " " + amount + " " + getString(R.string.money));
    }

    @Override
    public void setCashPayButton() {
        payButton.setText(getString(R.string.book));
    }

    @Override
    public void hideKeyboard() {
        Log.d(TAG, "Keyboard was hided");

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(phoneNumber.getWindowToken(), 0);

    }

    @Override
    public void onBackButton() {
        this.finish();
    }

    /**
     * Registration Fragment
     */
    @Override
    public void noUserError() {
        Log.d(TAG, "Start BookingPayActivity with ");
        Intent intent = new Intent(this, BookingPayActivity.class);
        intent.putExtra(Constants.EXTRA_MODE, Constants.MODE_NEW_USER);
        startActivity(intent);
    }

    /**
     * Start activity with the code fragment
     * @param code
     */
    @Override
    public void startAcceptNumber(String code) {
        Log.d(TAG, "Start BookingPayActivity with startAcceptNumberFragment");
        Intent intent = new Intent(this, BookingPayActivity.class);
        intent.putExtra(Constants.EXTRA_MODE, Constants.MODE_ALREADY_REGISTERED);
        intent.putExtra(Constants.CODE, code);
        startActivity(intent);
    }


    @Override
    public void showExcursions() {
        this.finish();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(Constants.SCREEN_INTENT,Constants.EXCURSIONS_SCREEN);
        startActivity(i);
    }

    @Override
    public void proceedPayment(int amount,int bookingId) {
        initPayment(amount,bookingId);
    }

    @Override
    public void setProperties(List<GroupProperty> properties) {
        String icon_name;
        Resources resources = this.getResources();
        for (int i = 0; i < properties.size(); i++) {

            TextView textView = new TextView(this);
            textView.setText(properties.get(i).getName());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,4,0,4);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setCompoundDrawablePadding(4);
            if (properties.get(i).getIcon() != null) {
                icon_name = properties.get(i).getIcon().split("[.]")[0];
                int picId = resources.getIdentifier(icon_name,
                        "drawable", getPackageName());
                if (Build.VERSION.SDK_INT > 17)
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(picId, 0, 0, 0);
            }
            propertiesLinearLayout.addView(textView);
        }
    }

    @Override
    public void setCompany(Operator operator) {
        companyTextView.setText(operator.getName());

    }

    @Override
    public void setPersonalData(String name, String number) {
        number = "+" + number;
        if (name != null)
            personalInfo.setText(name + ", " + number);
        else
            personalInfo.setText(number);
    }

    @Override
    public void setExcursionName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void setCategory(Category category) {
        if (category.getName() != null) {
            categoryTextView.setText(category.getName());

            if (category.getIcon() != null) {
                String icon_name = category.getIcon().split("[.]")[0];
                Resources resources = this.getResources();
                int picId = resources.getIdentifier(icon_name+"_white",
                        "drawable", getPackageName());
                if (Build.VERSION.SDK_INT > 17) {
                    categoryTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(picId, 0, 0, 0);
                }
            }
        }
    }

    @Override
    public void setTotalCost(int cost) {
        totalCost.setText(this.getString(R.string.total_cost) + " " + cost + "₽");
    }

    @Override
    public void setButtonText(String text) {
        payButton.setText(text);
    }

    @Override
    public void setAdultCost(int price) {
        adultEachPriceTextView.setText(price + "₽");
    }

    @Override
    public void setChildCost(int price) {
        childEachPriceTextView.setText(price + "₽");
    }

    @Override
    public void setImage(String src) {
        //Picasso.with(this.getApplicationContext()).load(src).fit().centerCrop().into(tourView);
        App.loadImageFromURL(src,tourView,this);
    }

    @Override
    public void setTimeDuration(String time, long duration) {
        timeAndDurationTextView.setText(App.makeTextFromDate(time,duration));
    }

    @Override
    public void onPay() {
        bookingPresenter.onPayButton();
    }

    @Override
    public void onConfirm() {
        //TODO replace depraceted method with new one
        hideKeyboard();
        String number = phoneNumber.getText().toString();
        if(!App.checkNumberFormat(number,getString(R.string.wrong_phone),getBaseContext())) return;
        bookingPresenter.onConfirmButton(App.transformPhone(number));

       // Log.d(TAG, number);
    }

    @Override
    public void showNewUserForm() {
        personalInfo.setVisibility(View.GONE);
        newUserForm.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOldUserForm() {
        newUserForm.setVisibility(View.GONE);
        personalInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetworkError() {
        NetworkError.onNetworkAccured(this);
    }

    private void onCardRB() {
        bookingPresenter.onCardRB();
    }

    private void onCashRB() {
        bookingPresenter.onCashRB();
    }

    private void onConditionCheckBox() {
        bookingPresenter.onConditionCheckBox();
    }

    @Override
    public void onStart(){
        super.onStart();

        SaveUser.readUser(this);

        bookingPresenter.onActivityStart();

        Log.d("Sequence", "onStart");
    }

}

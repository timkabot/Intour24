package com.innopolis.intour24.presenter.Impl;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.innopolis.intour24.App;
import com.innopolis.intour24.PhoneChecker;
import com.innopolis.intour24.model.LoginResponse;
import com.innopolis.intour24.model.SaveUser;
import com.innopolis.intour24.model.entity.Booking;
import com.innopolis.intour24.model.entity.BookingResponse;
import com.innopolis.intour24.model.entity.Excursion;
import com.innopolis.intour24.model.entity.Group;
import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.presenter.BookingPresenter;
import com.innopolis.intour24.view.BookingView;
import com.innopolis.intour24.view.Impl.BookingActivity;

import java.sql.SQLOutput;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ekaterina on 6/14/17.
 */

public class BookingPresenterImpl implements BookingPresenter {
    private static final String TAG = "BPIDebug";
    private BookingActivity bookingView;
    private int groupId = 0;
    private User user;
    private Group group;
    private int adultNumber;
    private int childNumber;

    private final static int NONE = 0;
    private final static int CARD = 1;
    private final static int CASH = 2;

    private int rbChoice = NONE;

    private boolean checkMark = false;
    private boolean buttonActivated = false;

    private int totalCost = 0;

    public static boolean isFromBooking = false;

    public BookingPresenterImpl(BookingActivity bookingView) {
        this.bookingView = bookingView;

        AppCompatActivity activity = bookingView;
        groupId = activity.getIntent().getIntExtra(GroupPresenterImpl.GROUP_ID, 0);
        SaveUser.readUser(bookingView.getApplicationContext());
        user = User.getInstance();
        getExcursionData();
    }

    @Override
    public void getExcursionData() {
        System.out.println(groupId + " group ID");
        Observable<Group> groupObservable = App.getAPI().getGroupById(groupId);

        groupObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError);
    }

    @Override
    public void onNext(Group group) {
        this.group = group;
        Excursion excursion = group.getExcursion();

        bookingView.setExcursionName(excursion.getName());
        bookingView.setAdultCost(excursion.getPrice().getAdultPrice());
        bookingView.setChildCost(excursion.getPrice().getChildrenPrice());
        bookingView.setCategory(excursion.getCategory());
        bookingView.setImage(excursion.getCover());
        bookingView.setTimeDuration(group.getTourDate(), group.getExcursion().getDuration());
        bookingView.setCompany(group.getExcursion().getOperator());
        bookingView.setUpAdultSpinner(group.getSeatsCapacity() - group.getSeatReserved());
        bookingView.setUpChildSpinner(group.getSeatsCapacity() - group.getSeatReserved());
        bookingView.setLogo(excursion.getOperator().getLogo());
        bookingView.setProperties(excursion.getProperties());

        updatePayButton();

        checkUserAuth();
    }

    @Override
    public void onConfirmButton(String number) {

        Log.d(TAG, "Confirm button was clicked" + " _------- " + number);
          user = User.getInstance();
          user.setPhone(number);
          checkInDB(number);

    }

    private void checkInDB(String number) {
        System.out.println(number);
        //TODO because now every response cost 1.5 ruble
        isFromBooking = true;
        Observable<LoginResponse> registerObservable = App.getAPI().checkPhone(number);
        registerObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNextPhoneCheck, this::onError);

    }

    public void onNextPhoneCheck(LoginResponse loginResponse){
        Log.d(TAG, "Checking loginResponse");
        //With registration
        if (loginResponse.getIsRegistered() > 0){
            //if it is already registered
            Log.d(TAG, "Start accept number");
            user.setId(loginResponse.getId());
            SaveUser.saveUser(bookingView.getApplicationContext());
            bookingView.startAcceptNumber(loginResponse.getCode());//Start Code accept fragment with code
        }else{
            //if not registered
            Log.d(TAG, "No such number in DB\n Start new user form");
            bookingView.noUserError();
        }
    }

    @Override
    public void onAdultItemSelected(int numberOfTourists) {
        adultNumber = numberOfTourists;
        updateCost();
    }

    @Override
    public void onChildItemSelected(int numberOfTourists) {
        childNumber = numberOfTourists;
        updateCost();
    }

    @Override
    public void onCardRB() {
        rbChoice = CARD;
        updatePayButton();
    }

    @Override
    public void onCashRB() {
        rbChoice = CASH;
        updatePayButton();
    }

    @Override
    public void onPayButton() {
        if (buttonActivated) {
            Log.d(TAG, "Pay Button Clicked");
            //TODO check response
            String date = group.getTourDate();
            date = date.substring(0,date.indexOf('+'));
            Observable<BookingResponse> bookingResponseObservable =
                    App.getAPI().booking(group.getId(),
                    user.getId(), adultNumber, childNumber,/*TODO enfantNumber*/0,
                    totalCost, date);
            bookingResponseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onNextBooking, this::onError);
        }
    }
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
            bookingView.onNetworkError();
        }

    private void onNextBooking(BookingResponse bookingResponse) {
        if(rbChoice == CARD)
            bookingView.proceedPayment(totalCost,bookingResponse.getId());
        else if( bookingResponse.getStatus().equals("OK"))
            bookingView.showExcursions();
    }

    @Override
    public void onConditionCheckBox() {
        checkMark = !checkMark;
        updatePayButton();
    }

    @Override
    public void onActivityStart() {
        checkUserAuth();
        updatePayButton();
    }

    private void checkUserAuth() {
         user = User.getInstance();
        System.out.println(user.getPhone() + " novyi phone");
        if (user.getPhone() == null)
            bookingView.showNewUserForm();
         else {
            bookingView.showOldUserForm();
            bookingView.setPersonalData(user.getName(), user.getPhone());
        }
    }

    private void updateCost() {
        totalCost = adultNumber * group.getExcursion().getPrice().getAdultPrice() + childNumber * group.getExcursion().getPrice().getChildrenPrice();
        bookingView.setTotalCost(totalCost);
    }

    /**
     * Button must be deactivated until all fields will be filled up
     */
    private void updatePayButton() {
        Log.d(TAG, "Radio button choice " + rbChoice + " Check Mark choice " + checkMark);
        if ((rbChoice != NONE) && (checkMark) && (user.getPhone() != null)) {
            bookingView.activatePayButton();
            buttonActivated = true;
        } else {
            bookingView.deactivatePayButton();
            buttonActivated = false;
        }

        if(rbChoice == CASH){
            bookingView.setCashPayButton();
        }else if(rbChoice == CARD){
            bookingView.setCardPayButton(totalCost);
        }
    }

}

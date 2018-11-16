package com.innopolis.intour24.presenter.Impl;

import android.content.Context;

import com.innopolis.intour24.App;
import com.innopolis.intour24.model.entity.Booking;
import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.presenter.BookedGroupsPresenter;
import com.innopolis.intour24.view.Impl.BookedGroupsFragment;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Timkabor on 6/20/2017.
 */

public class BookedGroupsPresenterImpl implements BookedGroupsPresenter {
    private BookedGroupsFragment view;
    private Context mContext;
    private ArrayList<Booking> bookings;
    @Override
    public void onPay() {
    }
    @Override
    public void getBookedGroups() {
        bookings.clear();
        Observable<ArrayList<Booking>> bookedGroups = App.getAPI().getBookingsById(User.getInstance().getId());
        bookedGroups.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookedGroupsData ->
                {
                    for(Booking b : bookedGroupsData)
                        if(b.getPaymentId() == 0) bookings.add(b);

                    view.updateRecyclerView(bookings);
                }
                , error -> view.showNetworkError()
                );
    }

    @Override
    public void designBuilding() {
        bookings = new ArrayList<>();
        view.createRecyclerView();
    }

    public BookedGroupsPresenterImpl(BookedGroupsFragment view)
    {
        this.view = view;
    }
}

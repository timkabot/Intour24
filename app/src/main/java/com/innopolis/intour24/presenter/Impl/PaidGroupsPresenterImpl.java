package com.innopolis.intour24.presenter.Impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.innopolis.intour24.App;
import com.innopolis.intour24.R;
import com.innopolis.intour24.model.entity.Booking;
import com.innopolis.intour24.model.entity.User;
import com.innopolis.intour24.presenter.PaidGroupsPresenter;
import com.innopolis.intour24.view.Impl.PaidGroupsFragment;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Timkabor on 6/21/2017.
 */

public class PaidGroupsPresenterImpl implements PaidGroupsPresenter {
    private PaidGroupsFragment view;
    private Context mContext;
    private ArrayList<Booking> bookings;
    @BindView(R.id.paidGroupsRecycler) RecyclerView recyclerView;

    @Override
    public void getBookedGroups() {

        bookings.clear();
        Observable<ArrayList<Booking>> bookedGroups = App.getAPI().getBookingsById(User.getInstance().getId());
        bookedGroups.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookedGroupsData ->
                {
                    for(Booking b : bookedGroupsData)
                        if(b.getPaymentId() != 0) bookings.add(b);
                    view.updateRecyclerView(bookings);
                },error -> view.showNetworkError());
    }
    @Override
    public void designBuilding() {
        bookings = new ArrayList<>();
        view.createRecyclerView();
    }
    public PaidGroupsPresenterImpl(PaidGroupsFragment view)
    {
        this.view = view;
    }

    public PaidGroupsFragment getView() {
        return view;
    }

    public void setView(PaidGroupsFragment view) {
        this.view = view;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}

package com.innopolis.intour24.view.Impl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innopolis.intour24.NetworkError;
import com.innopolis.intour24.R;
import com.innopolis.intour24.adapter.BookedGroupAdapter;
import com.innopolis.intour24.model.entity.Booking;
import com.innopolis.intour24.presenter.Impl.BookedGroupsPresenterImpl;
import com.innopolis.intour24.view.BookedGroupView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedGroupsFragment extends Fragment implements BookedGroupView{
    private BookedGroupAdapter  adapter;
    private ArrayList<Booking>  bookings;
    private LinearLayoutManager manager;
    private BookedGroupsPresenterImpl presenter;
    @BindView(R.id.bookedExcursions) RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_groups, container, false);
        ButterKnife.bind(this, view);

        presenter = new BookedGroupsPresenterImpl(this);
        presenter.designBuilding();
        presenter.getBookedGroups();

        return view;
    }
    public void createRecyclerView()
    {
        setBookings(new ArrayList<>());
        setAdapter(new BookedGroupAdapter(bookings,getActivity()));
        setManager(new LinearLayoutManager(getActivity()));

        recyclerView.setLayoutManager(getManager());
        recyclerView.setAdapter(getAdapter());
        recyclerView.setHasFixedSize(true);
    }
    public void updateRecyclerView(ArrayList<Booking> bookings)
    {
        this.bookings.clear();
        this.bookings.addAll(bookings);

        adapter.notifyDataSetChanged();
    }
    public BookedGroupAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BookedGroupAdapter adapter) {
        this.adapter = adapter;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public LinearLayoutManager getManager() {
        return manager;
    }

    public void setManager(LinearLayoutManager manager) {
        this.manager = manager;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
    public void showNetworkError()
    {
        NetworkError.onNetworkAccured(getActivity());
    }
}

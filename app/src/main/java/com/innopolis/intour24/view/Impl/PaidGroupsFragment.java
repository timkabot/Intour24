package com.innopolis.intour24.view.Impl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innopolis.intour24.R;
import com.innopolis.intour24.adapter.PaidGroupAdapter;
import com.innopolis.intour24.model.entity.Booking;
import com.innopolis.intour24.presenter.Impl.PaidGroupsPresenterImpl;
import com.innopolis.intour24.view.PaidGroupView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaidGroupsFragment extends Fragment implements PaidGroupView {
    private PaidGroupAdapter adapter;
    private ArrayList<Booking> bookings;
    private LinearLayoutManager manager;
    @BindView(R.id.paidGroupsRecycler) RecyclerView recyclerView;
    public PaidGroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paid_groups, container, false);
        ButterKnife.bind(this, view);

        PaidGroupsPresenterImpl presenter = new PaidGroupsPresenterImpl(this);
        presenter.designBuilding();
        presenter.getBookedGroups();
        return view;
    }
    public void createRecyclerView()
    {
        setBookings(new ArrayList<>());
        setAdapter(new PaidGroupAdapter(bookings,getActivity()));
        setManager(new LinearLayoutManager(getActivity()));

        recyclerView.setLayoutManager(getManager());
        recyclerView.setAdapter(getAdapter());
        recyclerView.setHasFixedSize(true);
    }
    @Override
    public void updateRecyclerView(ArrayList<Booking> bookings) {
        this.bookings.clear();
        this.bookings.addAll(bookings);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNetworkError() {

    }

    public PaidGroupAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(PaidGroupAdapter adapter) {
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
}

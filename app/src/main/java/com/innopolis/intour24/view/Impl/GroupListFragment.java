package com.innopolis.intour24.view.Impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innopolis.intour24.NetworkError;
import com.innopolis.intour24.R;
import com.innopolis.intour24.adapter.GroupAdapter;
import com.innopolis.intour24.model.entity.Group;
import com.innopolis.intour24.presenter.Impl.CatalogPresenterImpl;
import com.innopolis.intour24.presenter.Impl.GroupListPresenterImpl;
import com.innopolis.intour24.view.ExcursionsListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Timkabor on 5/29/2017.
 */

public class GroupListFragment extends Fragment implements ExcursionsListView {

    @BindView(R.id.excursionsRecycler) RecyclerView recyclerView;

    private GroupAdapter adapter;
    private GroupListPresenterImpl presenter;
    private LinearLayoutManager manager;
    private ArrayList<Group> groups;
    FragmentActivity thisActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_excursion_list, container, false);
        ButterKnife.bind(this, view);

        thisActivity = getActivity();

        presenter = new GroupListPresenterImpl(this);
        presenter.designBuilding();

        return view;
    }

    @Override
    public int getSightId() {
        return 1;
    }

    public void createRecyclerView() {
        setmExcursions(new ArrayList<>());

        setManager(new LinearLayoutManager(thisActivity));
        setAdapter(new GroupAdapter(groups, thisActivity));

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void updateRecyclerView(ArrayList<Group> groups) {
        this.groups.clear();
        this.groups.addAll(groups);
    }

    public void setManager(LinearLayoutManager manager) {
        this.manager = manager;
    }

    public void setAdapter(GroupAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void setEmptyResponseText(String text) {

    }

    public void setmExcursions(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public GroupListPresenterImpl getPresenter() {
        return presenter;
    }

    @Override
    public void appear() {
        ((MainActivity)thisActivity).showFragment(this);
    }

    @Override
    public void disappear() {
        ((MainActivity)thisActivity).hideFragment(this);
    }

    public void showNetworkError()
    {
        NetworkError.onNetworkAccured(getActivity());
    }

    public GroupAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}

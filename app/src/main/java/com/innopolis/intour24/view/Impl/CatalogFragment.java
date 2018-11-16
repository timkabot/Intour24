package com.innopolis.intour24.view.Impl;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.innopolis.intour24.NetworkError;
import com.innopolis.intour24.R;
import com.innopolis.intour24.adapter.SightAdapter;
import com.innopolis.intour24.model.entity.Sight;
import com.innopolis.intour24.presenter.CatalogPresenter;
import com.innopolis.intour24.presenter.Impl.CatalogPresenterImpl;
import com.innopolis.intour24.view.CatalogSightView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends Fragment implements CatalogSightView  {
    private static String TAG = "CFDebug";

    private CatalogPresenter mSightPresenter;
    private ArrayList<Sight> sightsList;
    private SightAdapter     mAdapter;
    private ArrayList<Sight> newSights;

    @BindView(R.id.progressBar)            ProgressBar mProgressBar;
    @BindView(R.id.overview_sign_recycler) RecyclerView mSightRecyclerView;

    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        initToolbar();
        checkConnection();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setHasOptionsMenu(true);
    }

    @Override
    public void setSightsRecyclerViewData(ArrayList<Sight> sights) {
        GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        sightsList = sights;
        //DBRepo db = new DBRepo(getActivity().getApplicationContext());
       // db.addSight(sightsList);
        mAdapter = new SightAdapter(sightsList, getActivity());
        mSightRecyclerView.setAdapter(mAdapter);
        mSightRecyclerView.setHasFixedSize(true);
        mSightRecyclerView.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.isHeader(position) ? manager.getSpanCount() : 1;
            }
        });
    }

    private void initToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.sign_toolbar));
    }

    private void checkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (mSightPresenter == null)
                mSightPresenter = new CatalogPresenterImpl(this);

            mSightPresenter.loadSights();

        } else
            showNoConnectionMessage();
    }

    @Override
    public void setEmptyResponseText(String text) {

    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoConnectionMessage() {
        Log.d(TAG, "No connection error!!");
        NetworkError.onNetworkAccured(getActivity());
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDestroy() {
        mSightPresenter.onDestroy();
        super.onDestroy();
    }
}

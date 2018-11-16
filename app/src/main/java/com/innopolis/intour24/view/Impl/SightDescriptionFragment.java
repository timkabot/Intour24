package com.innopolis.intour24.view.Impl;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.innopolis.intour24.App;
import com.innopolis.intour24.NetworkError;
import com.innopolis.intour24.R;
import com.innopolis.intour24.adapter.IconsGridViewAdapter;
import com.innopolis.intour24.model.entity.SightProperty;
import com.innopolis.intour24.presenter.Impl.SightDescriptionPresenterImpl;
import com.innopolis.intour24.presenter.SightDescriptionPresenter;
import com.innopolis.intour24.view.SightDescriptionView;

import org.joda.time.DateTime;

import java.util.ArrayList;

import at.blogc.android.views.ExpandableTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SightDescriptionFragment extends Fragment implements SightDescriptionView, DatePickerListener {

    @BindView(R.id.signDescription) ExpandableTextView signDescription;
    @BindView(R.id.datePicker) HorizontalPicker picker;
    @BindView(R.id.mainScrollView) ScrollView scrollView;
    @BindView(R.id.sightLogo) ImageView sightLogo;
    @BindView(R.id.gridViewIcons) ExpandableHeightGridView gridView;

    private AnimationOfLoadingExcursionFragment loadingExcursionFragment;
    private GroupListFragment listOfExcursionsFragment;
    private SightDescriptionPresenter presenter;
    private IconsGridViewAdapter adapter;
    private String myDate;
    private int sightId;
    private ArrayList<SightProperty> properties;
    private asyncAnimateBus animateBus;

    public SightDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sight_description, container, false);
        ButterKnife.bind(this, view);

        initToolbar();

        presenter = new SightDescriptionPresenterImpl(this);
        presenter.designBuilding();
        presenter.getSightInfo();
        scrollView.setSmoothScrollingEnabled(true);
        ((MainActivity)getActivity()).setItemsEnabled();
        loadingExcursionFragment.dissapear();
        return view;
    }

    private void initToolbar() {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.sign_toolbar));
    }

    public void setProperties(ArrayList<SightProperty> properties) {
        this.properties = properties;
        setIconsInfo();
    }

    public void requestSightId() {
        Bundle arguments = getArguments();
        setSightId(arguments.getInt("sightId"));
        System.out.println(sightId);
    }
    public void setSightId(int sightId) {
        this.sightId = sightId;
    }

    @Override
    public void changeToolbarTitle(String sightName) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(sightName);
    }

    @Override
    public void setSightDescription(String sightDescription) {
        this.signDescription.setText(sightDescription);
    }

    @Override
    public int getSightId() {
        return sightId;
    }

    @Override
    public void setSightLogo(String url) {
        App.loadImageFromURL(url, this.sightLogo,getContext());
    }


    public void setIconsInfo() {
        gridView.setExpanded(true);
        adapter = new IconsGridViewAdapter(getContext(),getProperties());
        gridView.setAdapter(adapter);
    }

    @Override
    public void buildDesign() {
        setFragments();
        buildCalendar();
        setupScrolling();

    }

    public void setFragments() {
        loadingExcursionFragment = (AnimationOfLoadingExcursionFragment) getChildFragmentManager().findFragmentById(R.id.busAnimContainter);
        listOfExcursionsFragment= (GroupListFragment) getChildFragmentManager().findFragmentById(R.id.excursionListFragment);
    }

    public ArrayList<SightProperty> getProperties() {
        return properties;
    }

    @Override
    public void setEmptyResponseText(String text) {

    }

    public void buildCalendar() {
        picker.setListener(this)
                .init();
        picker.setDate(new DateTime().plusDays(0));
        onDateSelected(DateTime.now());
    }

    public void setupScrolling() {
        signDescription.setAnimationDuration(1000L);
        signDescription.setInterpolator(new OvershootInterpolator());
        signDescription.setOnClickListener(view -> signDescription.toggle());
    }

    public String transformIntToDateFormat(int x) {
        if(x < 10) return "0" + x;
        return x + "";
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        myDate = getDateFormattedToRestAPI(dateSelected);
        if(animateBus != null) animateBus.cancel(true);
        animateBus = new asyncAnimateBus();
        animateBus.execute();


    }

    public void scrollToExcursionList() {
        scrollView.post(() -> scrollView.scrollTo(0, listOfExcursionsFragment.getRecyclerView().getTop()));
    }

    private class asyncAnimateBus extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            loadingExcursionFragment.appear();
            listOfExcursionsFragment.disappear();

            loadingExcursionFragment.startAnim();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (isCancelled()) return null;
            listOfExcursionsFragment.getPresenter().getGroups(myDate,sightId);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            listOfExcursionsFragment.getAdapter().notifyDataSetChanged();
            loadingExcursionFragment.stopAnim();
            loadingExcursionFragment.dissapear();
            listOfExcursionsFragment.appear();
            scrollToExcursionList();
        }
    }

    @Override
    public void showNetworkError() {
        NetworkError.onNetworkAccured(getActivity());
    }
    public String getDateFormattedToRestAPI(DateTime dateSelected) {
        int year = dateSelected.getYear();
        String month = transformIntToDateFormat(dateSelected.getMonthOfYear());
        String day = transformIntToDateFormat(dateSelected.getDayOfMonth());
        return year + "-" + month + "-" + day;
    }

}

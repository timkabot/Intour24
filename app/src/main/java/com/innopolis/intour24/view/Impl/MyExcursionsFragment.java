package com.innopolis.intour24.view.Impl;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innopolis.intour24.R;
import com.innopolis.intour24.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyExcursionsFragment extends Fragment {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    public MyExcursionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_excursions, container, false);
        ButterKnife.bind(this, view);

        initToolbar();
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.excursions_toolbar));
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PaidGroupsFragment(), getString(R.string.paid_excursions));
        adapter.addFragment(new BookedGroupsFragment(), getString(R.string.booked_excursions));
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

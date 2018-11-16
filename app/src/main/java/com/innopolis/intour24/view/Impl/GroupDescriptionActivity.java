package com.innopolis.intour24.view.Impl;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.innopolis.intour24.App;
import com.innopolis.intour24.NetworkError;
import com.innopolis.intour24.R;
import com.innopolis.intour24.model.entity.GroupProperty;
import com.innopolis.intour24.presenter.GroupPresenter;
import com.innopolis.intour24.presenter.Impl.GroupPresenterImpl;
import com.innopolis.intour24.view.ExcursionDescriptionView;

import java.util.List;

import at.blogc.android.views.ExpandableTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupDescriptionActivity extends AppCompatActivity implements ExcursionDescriptionView {

    private GroupPresenter presenter;

    @BindView(R.id.excursionNameTextView)         TextView excursionName;
    @BindView(R.id.costTextView)                  TextView cost;
    @BindView(R.id.addressTextView)               TextView address;
    @BindView(R.id.timeDurationTextView)          TextView timeDuration;
    @BindView(R.id.organizationCompanyTextView)   TextView organizationCompany;
    @BindView(R.id.categoryOfTransportTextView)   TextView categoryOfTransport;
    @BindView(R.id.childCostTextView)             TextView childCost;
    @BindView(R.id.babyCostTextView)              TextView babyCost;
    @BindView(R.id.bookButton)                    Button bookButton;
    @BindView(R.id.slider)                        SliderLayout slider;
    @BindView(R.id.descriptionExpandableTextView) ExpandableTextView descriptionTextView;
    @BindView(R.id.costIncludedTableLayout)       TableLayout costIncludesTableLayout;
    @BindView(R.id.includeInCostTextView)         TextView costIncludesHeadTextView;
    @BindView(R.id.logo)                          ImageView logo;
    @BindView(R.id.catLogo)                       ImageView categoryLogo;

    public GroupDescriptionActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_excursion_description);

        ButterKnife.bind(this);

        presenter = new GroupPresenterImpl(this);

        descriptionTextView.setOnClickListener((View v) -> descriptionTextView.toggle());

        bookButton.setOnClickListener(v -> onBookButtonClicked());

        checkButton();
    }

    public void checkButton()
    {
        Intent i = getIntent();
        assert i!=null;
        if(i.getExtras().getString("button") != null)
            presenter.checkButton(getIntent().getExtras().getString("button"));
    }

    @Override
    public void showPictures(List<String> src,String coverUrl) {

//        if (src.size() == 1){//if only one element in a slider view, replace with imageview
//            ImageView imageView = new ImageView(this);
//            imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
// RelativeLayout.LayoutParams.MATCH_PARENT));
//            //imageView.setBackgroundResource(src);
//
//            //File file = new File(src.get(0));
//            Picasso.with(this.getApplicationContext()).load(src.get(0)).fit().centerCrop()
//                    .placeholder(R.drawable.logo_color).into(imageView);
//            relLayoutImages.addView(imageView);
//            return;
//        }
        slider.addSlider(new DefaultSliderView(this).image(coverUrl).setScaleType(BaseSliderView.ScaleType.CenterCrop));
        for (String url : src) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView
                    .image(url)
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            slider.addSlider(sliderView);
        }

        if (src.size() == 1) {
            slider.stopAutoCycle();
        }
    }

    @Override
    public void setExcursionName(String name) {
        excursionName.setText(name);
    }

    @Override
    public void setTimeAndDuration(String time, long duration) {

        timeDuration.setText(App.makeTextFromDate(time,duration));
    }

    @Override
    public void setTransportType(String iconSrc, String categoryName) {
        if (categoryName != null) {
            categoryOfTransport.setText(categoryName);
        }
    }

    @Override
    public void setCompany(String companyName) {
        organizationCompany.setText(companyName);
    }

    @Override
    public void setPrice(int price) {
        cost.setText(this.getResources().getString(R.string.adult_price_tag) + ", \n" + String.valueOf(price) + getString(R.string.rubles));
    }

    @Override
    public void setChildPrice(int price) {
        childCost.setText(this.getResources().getString(R.string.child_price_tag) + ", " + String.valueOf(price) + getString(R.string.rubles));
    }

    @Override
    public void setBabyPrice(int price) {
        if (price > 0)
            babyCost.setText(this.getResources().getString(R.string.baby_price_tag) + ", " + String.valueOf(price) + getString(R.string.rubles));
        else
            babyCost.setText(this.getResources().getString(R.string.baby_price_tag) + ", \n" + this.getResources().getString(R.string.free_price));
    }

    @Override
    public void setDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void setStartPlace(String startPlace) {
        address.setText(startPlace);
    }

    @Override
    public void showMap(double lat, double lng) {
        showMapFragment();
        MapFragmentImpl mapFragment = MapFragmentImpl.getInstance();
        mapFragment.showPlace(lat, lng);

    }

    public void hideButton()
    {
        bookButton.setVisibility(View.GONE);
    }
    @Override
    public void onBookButtonClicked() {
        Intent intent = new Intent(this, BookingActivity.class);

        intent.putExtra(GroupPresenterImpl.GROUP_ID, getIntent().getIntExtra(GroupPresenterImpl.GROUP_ID, 0));

        startActivity(intent);
    }

    @Override
    public void onBackButtonClicked() {
        this.finish();
    }

    @Override
    public void onMapClicked() {
        //TODO now it in mapFragment class
    }

    @Override
    public void showNetworkError() {
        NetworkError.onNetworkAccured(this);
    }

    @Override
    public void setLogo(String url) {
        App.loadImageFromURL(url,logo,getApplicationContext());
    }

    @Override
    public void setCategoryLogo(String name) {
        App.setDrawableByName(name,categoryLogo,this);
    }

    @Override
    public void setProperties(List<GroupProperty> properties) {
        if (properties.get(0).getName() == null) {//No properties at all
            costIncludesHeadTextView.setVisibility(View.GONE);
            return;
        }
        TableRow tableRow;
        TextView firstTV;
        System.out.println(properties.size() + " razmer property");
        for (int i = 0; i < properties.size(); i ++) {
            tableRow = new TableRow(this);
            firstTV  = new TextView(this);

            firstTV.setText(properties.get(i).getName());
            firstTV.setTextColor(Color.BLACK);
            //secondTV.setText(properties.get(i + 1).getName());

            //int picId = App.getDrawableIdByName(properties.get(i).getIcon(),this);
            //firstTV.setCompoundDrawablesWithIntrinsicBounds(picId, 0, 0, 0);
            tableRow.addView(firstTV);
            //tableRow.addView(secondTV);

            costIncludesTableLayout.addView(tableRow);
        }
    }

    public void showMapFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mapFragment, MapFragmentImpl.getInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onStop() {
        slider.stopAutoCycle();
        super.onStop();
    }

    /**
     * Without it back button must be pressed twice, first to close fragment, second to close
     * activity
     */
    @Override
    public void onBackPressed() {
        this.finish();
    }
}

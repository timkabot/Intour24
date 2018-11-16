package com.innopolis.intour24.view;

import com.innopolis.intour24.model.entity.GroupProperty;

import java.util.List;

/**
 * Created by ekaterina on 6/1/17.
 */

public interface ExcursionDescriptionView {
    void showPictures(List<String> src,String coverUrl);
    void setExcursionName(String name);
    void setTimeAndDuration(String time, long duration);
    void setTransportType(String iconSrc, String categoryName);
    void setCompany(String companyName);
    void setPrice(int price);
    void setChildPrice(int price);
    void setBabyPrice(int price);
    void setDescription(String description);
    void setStartPlace(String startPlace);
    void showMap(double lat, double lng);
    void onBookButtonClicked();
    void onBackButtonClicked();
    void setProperties(List<GroupProperty> properties);
    void onMapClicked();
    void hideButton();
    void showNetworkError();
    void setLogo(String url);
    void setCategoryLogo(String name);
}

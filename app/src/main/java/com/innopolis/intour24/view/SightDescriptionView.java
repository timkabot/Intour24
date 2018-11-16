package com.innopolis.intour24.view;

import android.widget.ImageView;

import com.innopolis.intour24.model.entity.SightProperty;

import java.util.ArrayList;

/**
 * Created by Timkabor on 6/1/2017.
 */

public interface SightDescriptionView {
    void changeToolbarTitle(String sight);
    void setSightDescription(String sightDescription);
    void setSightLogo(String url);
    void setProperties(ArrayList<SightProperty> properties);
    void buildDesign();
    void setEmptyResponseText(String text);
    int getSightId();
    void requestSightId();
    void showNetworkError();
}

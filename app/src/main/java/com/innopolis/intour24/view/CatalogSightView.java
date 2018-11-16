package com.innopolis.intour24.view;

import com.innopolis.intour24.model.entity.Sight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей Пинкевич on 25.05.2017.
 */

public interface CatalogSightView {
    void setSightsRecyclerViewData(ArrayList<Sight> sights);
    void setEmptyResponseText(String text);
    void hideLoadingIndicator();
    void showNoConnectionMessage();
    void onClick();/*DEBUG*/
}

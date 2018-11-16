package com.innopolis.intour24.view;

import com.innopolis.intour24.model.entity.Group;
import com.innopolis.intour24.presenter.Impl.GroupListPresenterImpl;

import java.util.ArrayList;

/**
 * Created by Timkabor on 6/2/2017.
 */

public interface ExcursionsListView {
    int getSightId();
    void updateRecyclerView(ArrayList<Group> groups);
    void setEmptyResponseText(String text);
    GroupListPresenterImpl getPresenter();
    void showNetworkError();
    void appear();
    void disappear();
}

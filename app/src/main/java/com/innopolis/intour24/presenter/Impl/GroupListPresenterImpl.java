package com.innopolis.intour24.presenter.Impl;

import android.os.Looper;

import com.innopolis.intour24.App;
import com.innopolis.intour24.model.entity.Group;
import com.innopolis.intour24.presenter.GroupListPresenter;
import com.innopolis.intour24.view.Impl.GroupListFragment;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Timkabor on 6/2/2017.
 */

public class GroupListPresenterImpl implements GroupListPresenter {

    private GroupListFragment view;

    private ArrayList<Group> groups;

    public GroupListPresenterImpl(GroupListFragment view) {
        this.view = view;
    }

    @Override
    public void getGroups(String date,int sightId) {
        groups.clear();
        System.out.println(date + " - " + sightId);
        Observable<ArrayList<Group>> excursionObservable = App.getAPI().getGroupsByDate(date,sightId );
        excursionObservable.subscribeOn(Schedulers.newThread())
                .filter(integer -> {
                        System.out.println(Looper.getMainLooper().getThread() == Thread.currentThread());
                        return true;
                     })
                .subscribe(groupArrayList ->
                {
                    view.updateRecyclerView(groupArrayList);
                },
                        showNetworkError ->
                        {}
                        );
    }

    @Override
    public void designBuilding() {
        groups = new ArrayList<>();
        view.createRecyclerView();
    }
}

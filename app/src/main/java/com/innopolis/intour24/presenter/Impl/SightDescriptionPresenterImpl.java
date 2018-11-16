package com.innopolis.intour24.presenter.Impl;

import android.content.Context;
import android.util.Log;

import com.innopolis.intour24.App;
import com.innopolis.intour24.model.entity.Sight;
import com.innopolis.intour24.presenter.SightDescriptionPresenter;
import com.innopolis.intour24.view.Impl.SightDescriptionFragment;
import com.innopolis.intour24.view.SightDescriptionView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Timkabor on 6/2/2017.
 */

public class SightDescriptionPresenterImpl implements SightDescriptionPresenter {

    private SightDescriptionView view;
    private int sightId;
    private Sight mSight;
    private Context context;

    public SightDescriptionPresenterImpl(SightDescriptionView view) {
        this.context = ((SightDescriptionFragment)view).getActivity().getApplicationContext();
        this.view = view;
    }

    @Override
    public void designBuilding()
    {
        view.buildDesign();
    }

    @Override
    public void getSightInfo() {
        view.requestSightId();
        setSightId(view.getSightId());
        Observable<Sight> sightsObservable = App.getAPI().getSightInfo(sightId);
        sightsObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sight ->
                {
                    Log.d("DEBUGTAG", sight.getName());
                    mSight = sight;

                    if (sight.equals(null))
                        view.setEmptyResponseText("There is no such sight in database");
                    else {
                        System.out.println(sightId + "" + sight.getName());
                        view.changeToolbarTitle(sight.getName());
                        view.setSightDescription(sight.getDescription());
                        view.setSightLogo(sight.getCover());
                        view.setProperties(sight.getProperties());
                    }
                }
                        ,showNetworkError -> view.showNetworkError()
                          );
    }

    public void setSightId(int sightId) {
        this.sightId = sightId;
    }
}
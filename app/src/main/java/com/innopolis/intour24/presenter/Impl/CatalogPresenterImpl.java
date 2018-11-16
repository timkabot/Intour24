package com.innopolis.intour24.presenter.Impl;

import android.content.Context;
import android.util.Log;

import com.innopolis.intour24.App;
import com.innopolis.intour24.database.DBRepo;
import com.innopolis.intour24.model.entity.Sight;
import com.innopolis.intour24.presenter.CatalogPresenter;
import com.innopolis.intour24.view.CatalogSightView;
import com.innopolis.intour24.view.Impl.CatalogFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sergey Pinkevich on 01.06.2017.
 */

public class CatalogPresenterImpl implements CatalogPresenter {

    private static final String TAG = "Search query";
    private CatalogSightView view;
    private Context mContext;
    private DBRepo mRepo;

    public CatalogPresenterImpl(CatalogSightView view) {
        this.view = view;
        mContext = ((CatalogFragment) view).getActivity().getApplicationContext();
        mRepo = new DBRepo(mContext);
    }

    @Override
    public void loadSights() {
        ArrayList<Sight> sights = new ArrayList<>();
        Observable<List<Sight>> sightsObservable = App.getAPI().getSights();
        sightsObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sightData ->
                {
                    sights.addAll(sightData);
                    Log.d("DEBUGTAG", sightData.get(0).getName());
                    if (sights.isEmpty())
                        view.setEmptyResponseText("There is bad response");
                    else {
                        view.setSightsRecyclerViewData(sights);
                    }
                    view.hideLoadingIndicator();
                }
                        ,showNetworkError -> view.showNoConnectionMessage()
                );
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}

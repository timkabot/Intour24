package com.innopolis.intour24.presenter.Impl;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.innopolis.intour24.App;
import com.innopolis.intour24.model.entity.Excursion;
import com.innopolis.intour24.model.entity.Group;
import com.innopolis.intour24.presenter.GroupPresenter;
import com.innopolis.intour24.view.ExcursionDescriptionView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ekaterina on 6/2/17.
 */

public class GroupPresenterImpl implements GroupPresenter {

    private ExcursionDescriptionView excursionScreen;
    private int groupId;
    private final static String TAG = "EPDebug";
    public final static String GROUP_ID = "groupId";

    public GroupPresenterImpl(ExcursionDescriptionView excursionScreen) {
        this.excursionScreen = excursionScreen;
        AppCompatActivity activity = (AppCompatActivity) excursionScreen;
        groupId = activity.getIntent().getIntExtra(GROUP_ID, 0);//TODO
        System.out.println(groupId + " eto id");
        //dbRepo = new DBRepo(activity.getApplicationContext());
        getExcursionData();
    }

    /**
     * Retrieves all information from server
     */
    @Override
    public void getExcursionData() {
        Observable<Group> groupObservable = App.getAPI().getGroupById(groupId);

        Log.d(TAG, "Trying to get excursion data");

        if (groupObservable == null) {
            Log.d(TAG, "Something went wrong");
        }

        groupObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError);
    }

    @Override
    public void checkButton(String s) {
        if(s.equals("noButton")) excursionScreen.hideButton();
    }

    public void setupMap(Excursion excursion) {
        //TODO
    }

    private void onNext(Group group) {
        Excursion excursion = group.getExcursion();

        if (excursion.getError() == null) {
            Log.d(TAG, excursion.getName());
            excursionScreen.setExcursionName(excursion.getName());
            excursionScreen.setPrice(excursion.getPrice().getAdultPrice());

            excursionScreen.setBabyPrice(excursion.getPrice().getEnfantPrice());
            excursionScreen.setChildPrice(excursion.getPrice().getChildrenPrice());

            excursionScreen.setTransportType(excursion.getCategory().getIcon(), excursion.getCategory().getName());

            excursionScreen.setTimeAndDuration(group.getTourDate(), excursion.getDuration());

            excursionScreen.setDescription(excursion.getDescription());
            excursionScreen.setStartPlace(excursion.getPickingPlace().getName());

            excursionScreen.setCompany(excursion.getOperator().getName());
            excursionScreen.setLogo(excursion.getOperator().getLogo());
            excursionScreen.setCategoryLogo(excursion.getCategory().getIcon());

                    /*DEBUG*/
            //excursionScreen.setProperties(properties);
                    /*REPLACE WITH*/
            excursionScreen.setProperties(excursion.getProperties());
                    /*END_DEBUG*/

            excursionScreen.showPictures(excursion.getImages(),excursion.getCover());

                    /*DEBUG*/
//            excursionScreen.showMap(55.798457, 49.105130);
                    /*REPLACE_WITH*/
           if(excursion.getPickingPlace().getGeoposition() != null ){
               excursionScreen.showMap(excursion.getPickingPlace().getLag(), excursion.getPickingPlace().getLng());
               setupMap(excursion);
           }
                    /*END_DEBUG*/

        }
    }
    private void onError(Throwable error) {
        error.printStackTrace();
        excursionScreen.showNetworkError();
    }
}
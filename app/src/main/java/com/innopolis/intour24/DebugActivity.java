
package com.innopolis.intour24;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;


import com.innopolis.intour24.database.DBRepo;
import com.innopolis.intour24.model.entity.Excursion;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ekaterina on 6/13/17.
 */

public class DebugActivity extends AppCompatActivity {
    private final String TAG = "DADebug";
    private DBRepo dbRepo;

    @BindView(R.id.addExcursionDB) Button addExDB;
    @BindView(R.id.getExcursionDB) Button getExDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_layout);

        ButterKnife.bind(this);
        addExDB.setOnClickListener(v -> onAddExcursionDB());
        getExDB.setOnClickListener(v -> onGetExcursionDB());

        dbRepo = new DBRepo(this);
    }

    public void onAddExcursionDB(){
////        Excursion excursion = new Excursion();
////        excursion.setId(0);
////        excursion.setName("Казанский Кремль");
////        excursion.setPrice(1000);
////        excursion.setPriceForChildren(500);
////        excursion.setPriceForBaby(100);
////        excursion.setStartTime(123);
////        excursion.setDuration(6000);
////        excursion.setCapacity(25);
////        excursion.setDescription("Посетите потрясающий Казанский белокаменный Кремль");
////        excursion.setPickingPlace("Комбинат Здоровья");
////        excursion.setCategory("Автобусная Экскурсия");
////        excursion.setSchedule("Посетим множество различных мест");
////        excursion.setRating(1.2);
////        excursion.setCompany("Экскурсии Татарстана");
////        excursion.setLat(1.12345678901234567890);
////        excursion.setLng(1.12345678901234567890);
////
////        ArrayList<String> imgSrc = new ArrayList<>();
////        imgSrc.add("FirstImage");
////        imgSrc.add("SecondImage");
////        imgSrc.add("ThirdImage");
//
//        excursion.setImages(imgSrc);
//
//        ArrayList<GroupProperty> properties = new ArrayList<>();
//        properties.add(new GroupProperty("Bus icon", "Transportation included"));
//        properties.add(new GroupProperty("Food icon", "Food included"));
//
//        excursion.setProperties(properties);
//
//        dbRepo.addExcursion(excursion);
//        Log.d(TAG, "Excursion was added!");
    }

    public void onGetExcursionDB(){
        Excursion excursion = dbRepo.getExcursionById(0);
        Log.d(TAG, "Excursion was retrieved");
        Log.d(TAG, excursion.toString());
    }
}
package com.innopolis.intour24.database;

import com.innopolis.intour24.model.entity.Excursion;
import com.innopolis.intour24.model.entity.Sight;

import java.util.ArrayList;

/**
 * Created by ekaterina on 6/2/17.
 */

public interface DBRepoInterface {
    ArrayList<Excursion> getExcursionList(String signName);

    ArrayList<Sight> getSightsList();

    ArrayList<Sight> getSights(String tourName);

    void addExcursion(Excursion excursion);

    void addExcursions(ArrayList<Excursion> lExcursion);

    void addSight(Sight sight);

    void addSight(ArrayList<Sight> lSight);

    void addRel(Sight sight, Excursion excursion);

    void addRel(String signName, String excursionName);

    boolean isInTable(int signId, int excursionId);

    ArrayList<Sight> searchSights(String text);

    Sight getSight(String signName);

    Excursion getExcursion(String name);
}

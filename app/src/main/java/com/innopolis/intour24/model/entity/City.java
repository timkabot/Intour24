package com.innopolis.intour24.model.entity;

import java.util.ArrayList;

/**
 * Created by Timkabor on 5/24/2017.
 */

public class City
{
    private ArrayList<Sight> mSights;
    private String country;
    private String name;
    private String nativeLanguage; // native language of the people in city

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City(String name) {
        this.name = name;
        mSights = new ArrayList<>();
    }

    void addSign(Sight s)
    {
        mSights.add(s);
    }

    public ArrayList<Sight> getSights()
    {
        return mSights;
    }

    public void setSights(ArrayList<Sight> sights) {
        sights = sights;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        nativeLanguage = nativeLanguage;
    }

    public ArrayList<Sight> getmSights() {
        return mSights;
    }

    public void setmSights(ArrayList<Sight> mSights) {
        this.mSights = mSights;
    }
}

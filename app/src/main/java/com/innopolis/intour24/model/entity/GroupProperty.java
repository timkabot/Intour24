package com.innopolis.intour24.model.entity;

import android.os.Parcel;

/**
 * Created by Timkabor on 6/6/2017.
 */

public class GroupProperty {
    private int id;
    private String name;
    private String icon;

    public GroupProperty(String icon, String name){
        this.name = name;
        this.icon = icon;
    }

    protected GroupProperty(Parcel in) {
        name = in.readString();
        icon = in.readString();
    }

    public GroupProperty(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

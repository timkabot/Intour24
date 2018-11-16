package com.innopolis.intour24.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Timkabor on 6/6/2017.
 */

public class SightProperty implements Parcelable {
    private String name;
    private String icon;

    protected SightProperty(Parcel in) {
        name = in.readString();
        icon = in.readString();
    }

    public static final Creator<SightProperty> CREATOR = new Creator<SightProperty>() {
        @Override
        public SightProperty createFromParcel(Parcel in) {
            return new SightProperty(in);
        }

        @Override
        public SightProperty[] newArray(int size) {
            return new SightProperty[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(icon);
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

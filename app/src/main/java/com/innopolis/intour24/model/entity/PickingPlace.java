package com.innopolis.intour24.model.entity;

/**
 * Created by ekaterina on 6/13/17.
 */

public class PickingPlace {
    private int id;
    private String name;
    private String geoposition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeoposition() {
        return geoposition;
    }

    public void setGeoposition(String geoposition) {
        this.geoposition = geoposition;
    }

    public double getLng(){
        double result = Double.valueOf(geoposition.split(",")[1].trim());
        return result;
    }

    public double getLag(){
        double result = Double.valueOf(geoposition.split(",")[0].trim());
        return result;
    }
}

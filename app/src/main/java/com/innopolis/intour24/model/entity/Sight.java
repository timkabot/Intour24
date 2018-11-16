package com.innopolis.intour24.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekaterina on 5/24/17.
 */

public class Sight {

    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    private ArrayList<SightProperty> properties;

    private String geoposition;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("minPrice")
    @Expose
    private int minPrice;

    @SerializedName("maxPrice")
    @Expose
    private int maxPrice;

    @SerializedName("cover")
    @Expose
    private String cover;

    private List<String> images;

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Sight() {}

    public Sight(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeoposition() {
        return geoposition;
    }

    public void setGeoposition(String geoposition) {
        this.geoposition = geoposition;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<SightProperty> getProperties() {
        return properties;
    }
}

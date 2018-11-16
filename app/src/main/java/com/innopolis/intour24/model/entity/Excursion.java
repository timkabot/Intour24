package com.innopolis.intour24.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Timkabor on 5/24/2017.
 */

public class Excursion {

    private int id;
    private long startTime;
    private long duration;
    private String name;
    private String description;
    private int capacity;
    private boolean isPicking;
    private String schedule;
    private double rating;
    private List<String> images;
    private Category category;
    private PickingPlace pickingPlace;
    private Price price;
    private List<GroupProperty> properties;
    private String error;
    private Operator operator;
    private String cover;
    public Excursion() {}

    public String getName() {
        return name;
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isPicking() {
        return isPicking;
    }

    public void setPicking(boolean picking) {
        isPicking = picking;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PickingPlace getPickingPlace() {
        return pickingPlace;
    }

    public void setPickingPlace(PickingPlace pickingPlace) {
        this.pickingPlace = pickingPlace;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<GroupProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<GroupProperty> properties) {
        this.properties = properties;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setName(String name) {
        this.name = name;
    }
}
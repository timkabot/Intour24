package com.innopolis.intour24.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Timkabor on 6/7/2017.
 */

public class Group {

    @SerializedName("id")
    private int id;

    @SerializedName("tourDate")
    private String tourDate;

    @SerializedName("seatsReserved")
    private int seatReserved;

    @SerializedName("guideId")
    private int guideId;

    @SerializedName("seatsCapacity")
    private int seatsCapacity;

    @SerializedName("excursion")
    private Excursion excursion;

    public int getId() {
        return id;
    }

    public String getTourDate() {
        return tourDate;
    }

    public int getSeatReserved() {
        return seatReserved;
    }

    public int getGuideId() {
        return guideId;
    }

    public int getSeatsCapacity() {
        return seatsCapacity;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTourDate(String tourDate) {
        this.tourDate = tourDate;
    }

    public void setSeatReserved(int seatReserved) {
        this.seatReserved = seatReserved;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public void setSeatsCapacity(int seatsCapacity) {
        this.seatsCapacity = seatsCapacity;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }
}
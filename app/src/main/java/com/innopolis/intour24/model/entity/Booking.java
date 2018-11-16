package com.innopolis.intour24.model.entity;

/**
 * Created by Timkabor on 6/20/2017.
 */

public class Booking {
    private int id;
    private Excursion excursion;
    private int totalPrice;
    private int paymentId;
    private Group group;
    private int isCancelled;
    private String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public int getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(int isCancelled) {
        this.isCancelled = isCancelled;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}

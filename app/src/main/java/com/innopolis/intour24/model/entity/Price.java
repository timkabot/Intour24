package com.innopolis.intour24.model.entity;

/**
<<<<<<< HEAD
 * Created by Timkabor on 6/8/2017.
=======
 * Created by ekaterina on 6/13/17.
>>>>>>> ab95d3e94d82d26690bb13e651c3fd3aabc238a2
 */

public class Price {
    private int id;
    private int priceForChildren;
    private int priceForAdult;
    private int priceForEnfant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildrenPrice() {
        return priceForChildren;
    }

    public void setChildrenPrice(int price_for_children) {
        this.priceForChildren = price_for_children;
    }

    public int getAdultPrice() {
        return priceForAdult;
    }

    public void setAdultPrice(int price_for_adult) {
        this.priceForAdult = price_for_adult;
    }

    public int getEnfantPrice() {
        return priceForEnfant;
    }

    public void setEnfantPrice(int price_for_enfant) {
        this.priceForEnfant = price_for_enfant;
    }
}

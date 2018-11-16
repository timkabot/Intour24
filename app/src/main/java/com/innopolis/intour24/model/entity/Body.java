package com.innopolis.intour24.model.entity;

/**
 * Created by Timkabor on 6/28/2017.
 */

public class Body {
    int groupId,touristId,adults,children,enfants,totalPrice;
    String createDateTime;

    public Body(int groupId, int touristId, int adults, int children, int enfants, int totalPrice, String createDateTime) {
        this.groupId = groupId;
        this.touristId = touristId;
        this.adults = adults;
        this.children = children;
        this.enfants = enfants;
        this.totalPrice = totalPrice;
        this.createDateTime = createDateTime;
    }
}

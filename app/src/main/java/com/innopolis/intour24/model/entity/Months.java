package com.innopolis.intour24.model.entity;

/**
 * Created by Timkabor on 6/20/2017.
 */

public  class Months {
    public static String getMonth(int id)
    {
        if(id == 1) return "January";
        if(id == 2) return "February";
        if(id == 3) return "March";
        if(id == 4) return "April";
        if(id == 5) return "May";
        if(id == 6) return "June";
        if(id == 7) return "July";
        if(id == 8) return "August";
        if(id == 9) return "September";
        if(id == 10) return "October";
        if(id == 11) return "November";
        if(id == 12) return "December";
        else return "Invalid month month";
    }
}

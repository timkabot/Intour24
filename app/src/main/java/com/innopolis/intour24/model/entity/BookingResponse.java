package com.innopolis.intour24.model.entity;

/**
 * Created by ekaterina on 6/21/17.
 */

public class BookingResponse {
    private String status;
    private int full;
    private int id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

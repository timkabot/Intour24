package com.innopolis.intour24.model.entity;

/**
 * Created by Sergey Pinkevich on 05.06.2017.
 */

public class User {
    private int id;
    private String name;
    private String phone;
    private static User instance;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";

    private User() {}

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
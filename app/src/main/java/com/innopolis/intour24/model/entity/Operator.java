package com.innopolis.intour24.model.entity;

/**
<<<<<<< HEAD
 * Created by Timkabor on 6/16/2017.
=======
 * Created by ekaterina on 6/14/17.
>>>>>>> 50a33954327820f8b42faa7dad12bef88cbf32b2
 */

public class Operator {
    private int id;
    private String name;
    private String phone;
    private String logo;
    private boolean accreditation;
    private String email;
    private String address;

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


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isAccreditation() {
        return accreditation;
    }

    public void setAccreditation(boolean accreditation) {
        this.accreditation = accreditation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
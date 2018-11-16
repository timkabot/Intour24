package com.innopolis.intour24.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergey Pinkevich on 14.06.2017.
 */

public class RegisterResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

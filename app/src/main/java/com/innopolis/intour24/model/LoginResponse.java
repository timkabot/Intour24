package com.innopolis.intour24.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergey Pinkevich on 14.06.2017.
 */

public class LoginResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    @SerializedName("registered")
    private int isRegistered;

    private int id;
    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public int getIsRegistered() {
        return isRegistered;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIsRegistered(int isRegistered) {
        this.isRegistered = isRegistered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.innopolis.intour24.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Timkabor on 6/29/2017.
 */

public class PaymentResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("createDatetime")
    private String createDatetime;

    @SerializedName("registered")
    private int paymentId;

    private int id;
    public String getStatus() {
        return status;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

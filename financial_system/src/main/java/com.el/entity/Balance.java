package com.el.entity;

import javax.persistence.Entity;

/**
 * Created by Ak_Guili on 2017/4/7.
 */
@Entity
public class Balance {
    private String amount;
    private String date;
    private String desc;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

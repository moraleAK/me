package com.el.entity;

import javax.persistence.Entity;

/**
 * Created by Ak_Guili on 2017/4/7.
 */
@Entity
public class User {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

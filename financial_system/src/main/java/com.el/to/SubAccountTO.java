package com.el.to;

/**
 * Created by Ak_Guili on 2017/5/17.
 */
public class SubAccountTO {
    private String account;
    private long amount;
    private long recordAmount;

    public long getRecordAmount() {
        return recordAmount;
    }

    public void setRecordAmount(long recordAmount) {
        this.recordAmount = recordAmount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAccount() {

        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

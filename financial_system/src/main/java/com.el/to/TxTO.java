package com.el.to;

/**
 * Created by Ak_Guili on 2017/5/17.
 */
public class TxTO {
    private String fromAccount;
    private String toAccount;
    private long txNo;
    private long amount;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public long getTxNo() {
        return txNo;
    }

    public void setTxNo(long txNo) {
        this.txNo = txNo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}

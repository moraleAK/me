package com.el;

import com.el.to.AccountTO;

/**
 * Created by Ak_Guili on 2017/5/11.
 */
public class ObjectCompare {
    public void test(){
        AccountTO accountTO = new AccountTO();
        AccountTO accountTO1 = new AccountTO();

        accountTO.setPhoneNo("18655445270");
        accountTO1.setPhoneNo("18655445260");

    }


}

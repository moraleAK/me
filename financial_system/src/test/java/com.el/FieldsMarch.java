package com.el;

import com.el.to.AccountTO;
import com.el.to.UserTO;
import com.el.util.MarchFields;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by Ak_Guili on 2017/5/9.
 */
public class FieldsMarch {
    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws IllegalAccessException {
        UserTO userTO = new UserTO();
        AccountTO accountTO = new AccountTO();
        userTO.setUserName("ak");
        userTO.setPassword("gcc");
        userTO.setAddress("*************");
        userTO.setPhoneNo("ssssssssssssssssssssss");

        long s = System.currentTimeMillis();
        //for(int i = 0; i < 10000000;i++ ) {
        MarchFields.marchFields(userTO, accountTO);
        MarchFields.object2Map(userTO);
//            accountTO.setPassword(userTO.getPassword());
//            accountTO.setAccountName(userTO.getUserName());
//            accountTO.setAddress(userTO.getAddress());
//            accountTO.setPhoneNo(userTO.getPhoneNo());
        //}
        System.out.println(System.currentTimeMillis() - s);
        System.out.println();
    }
}

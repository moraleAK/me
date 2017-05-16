package com.el;

import org.junit.Test;

/**
 * Created by Ak_Guili on 2017/5/10.
 */
public class typeJudge {
    @Test
    public void test(){
        long a = 0;
        Object o =a;
        if(o instanceof Long){
            System.out.println("******************************");
        }
    }
}

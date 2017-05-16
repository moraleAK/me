package com.el;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ak_Guili on 2017/5/15.
 */
public class RegexpTest {
    public static boolean isNum(String str){
        String regexp = "^\\+?[1,9][0,9]*$";
        String regexp1 = "^\\+?[1-9][0-9]*$";
        String regexp2 = "^[0-9]*$";
        return Pattern.matches(regexp2, str);
    }

    @Test
    public void isNumTest(){
        System.out.println(isNum("-100"));
    }
}

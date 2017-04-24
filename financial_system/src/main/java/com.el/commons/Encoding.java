package com.el.commons;

import java.io.UnsupportedEncodingException;

/**
 * Created by Ak_Guili on 2017/4/24.
 */
public class Encoding {
    public static String getEncoding(String str) throws UnsupportedEncodingException {
        String encode = "GBK";
        if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GBK
            return encode;      //是的话，返回“GBK“，以下代码同理
        }

        encode = "ISO-8859-1";
        if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是ISO-8859-1
            return encode;
        }

        encode = "UTF-8";
        if (str.equals(new String(str.getBytes(encode), encode))) {   //判断是不是UTF-8
            return encode;
        }

        encode = "GB2312";
        if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GB2312
            return encode;
        }
        return "";        //如果都不是，说明输入的内容不属于常见的编码格式。
    }
}

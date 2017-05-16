package com.el.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Ak_Guili on 2017/5/9.
 */
public class MarchFields {
    static Logger LOG = LoggerFactory.getLogger(MarchFields.class);
    public static Object marchFields(Object var1 ,Object var2 ) throws IllegalAccessException {
        Class clazz1 = var1.getClass();
        Class clazz2 = var2.getClass();
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();
        for (Field field : fields1) {
            for(Field field1 : fields2){
                if(field.getName().equals(field1.getName()) && field.getType().equals(field1.getType())){
                    field1.setAccessible(true);
                    field.setAccessible(true);
                    field1.set(var2,field.get(var1));
                    LOG.info("{}:{}",field1.getName(),field1.get(var2));
                }
            }
        }
        return var2;
    }

    public static HashMap<String, Object> object2Map(Object o) throws IllegalAccessException {
        Class clazz = o.getClass();
        HashMap hashMap = new HashMap();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            hashMap.put(field.getName(),field.get(o));
            field.setAccessible(false);
        }
        return hashMap;
    }

    public static String object2String(Object o){
        Class clazz = o.getClass();
        String s1 = "\"";
        String s2 = "\",";
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder strb = new StringBuilder("{");
        for(Field field : fields){
            field.setAccessible(true);
            strb.append("");
            field.setAccessible(false);
        }

        return "";
    }
}

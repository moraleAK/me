package com.el;

import com.el.to.UserTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Ak_Guili on 2017/4/7.
 */
public class ReflectTest {
    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        UserTO userTO = new UserTO();
        userTO.setPassword("111111");
       // userTO.setUserName("ak_gcc");
        Class aClass = userTO.getClass();
        //只能获取声明为 public 的 fields
        Field[] fields = aClass.getFields();
        //获取声明 fields
        //Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            //运行时赋值
            field.setAccessible(true);
            field.set(userTO,"hhhhhh");
            LOG.info("{}", field.get(userTO));
            LOG.info("{}", field.getName());
        }
        LOG.info("*******************************************");
        Method[] methods = aClass.getDeclaredMethods();
       // Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            //调用函数
            LOG.info("method:{}",method);
            LOG.info("methodName:{}",method.getName().startsWith("get"));
            if(method.getName().startsWith("set")){
                LOG.info("setValue:{}",method.invoke(userTO,"hhhhhhhhhhhhhh"));
            }
            if(method.getName().startsWith("get")){
                LOG.info("getValue:{}",method.invoke(userTO));
               // System.out.println("getValue:" + method.invoke(userTO));
            }
        }

        LOG.info("****************************************");
        Class<?> clazz = Class.forName("com.el.ReflectTest");
        LOG.info("classLoader:{}",clazz.getClassLoader());
        LOG.info("clazz:{}",clazz.getName());
    }

}

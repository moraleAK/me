package com.el;

import com.el.to.UserTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Ak_Guili on 2017/4/7.
 */
public class ReflectTest {
    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        UserTO userTO = new UserTO();
        userTO.setPassword("111111");
       // userTO.setUserName("ak_gcc");
        Class aClass = userTO.getClass();
        //只能获取声明为 public 的 fields
        Field[] fields = aClass.getFields();
        //获取声明 fields
        //Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(userTO,"hhhhhh");
            LOG.info("{}", field.get(userTO));
            LOG.info("{}", field.getName());
        }
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            LOG.info("method:{}",method);
            LOG.info("methodName:{}",method.getName().startsWith("get"));
        }

        Class clazz = null;
        clazz.forName("com.el.to.UserTO", true, new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        });
        LOG.info("clazz:{}",clazz);

    }

}

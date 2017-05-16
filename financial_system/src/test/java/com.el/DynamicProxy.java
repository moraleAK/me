package com.el;

import com.el.proxy.cglib.CglibProxy;
import com.el.proxy.jdk.MyInvocationHandler;
import com.el.service.UserService;
import com.el.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Ak_Guili on 2017/5/9.
 */
public class DynamicProxy {
    //@Test
    //jdkProxy
    public static void main(String[] args){
        UserService userService = new UserServiceImpl();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), invocationHandler);
        System.out.println(System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFiles"));
        System.out.println(userServiceProxy.getUserName());
        System.out.println(userServiceProxy.updatePassword());
    }

    @Test
    public void cglibProxyTest(){
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);

        UserService o = (UserService)enhancer.create();
        o.getUserName();
        o.updatePassword();
    }

}


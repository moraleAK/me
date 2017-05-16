package com.el.service.impl;

import com.el.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ak_Guili on 2017/5/9.
 */
public class UserServiceImpl implements UserService {
    static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public String getUserName() {
        LOG.info("userName = test;");
        return "test";
    }

    @Override
    public boolean updatePassword() {
        LOG.info("updatePassword success!");
        return true;
    }
}

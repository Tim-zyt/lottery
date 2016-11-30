package com.sf.lottery.client.user.service.impl;

import com.sf.lottery.client.user.service.UserService;
import com.sf.lottery.common.model.SignedUser;
import com.sf.lottery.common.model.User;
import com.sf.lottery.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 01139954 on 2016/11/30.
 */
public class UserServiceImpl implements UserService {
    @Autowired UserManager userManager;

    @Override
    public boolean saveUser(User user) {
        return userManager.addUser(user);
    }

    @Override public List<SignedUser> getIsSignUser() {
        return null;
    }
}

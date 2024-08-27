package com.kinbo.boot2deep.facade;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author songjunbao
 * @date 2024-08-07
 */
@DubboService(protocol = "dubbo")
public class UserProviderImpl implements UserProvider{

    @Override
    public User getUser(int userId) {
        User user = new User();
        user.setId(String.valueOf(userId));
        user.setAge(32);
        user.setName("junbao");
        return user;
    }
}

package com.kinbo.boot2deep.controller;

import com.kinbo.boot2deep.aspect.UserLog;
import com.kinbo.boot2deep.dao.UserMapper;
import com.kinbo.boot2deep.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/add")
    @UserLog(module = "用户管理", operate = "添加用户")
    public int addUser(String name, int age){
        return userMapper.insertOne(name, age);
    }


    @RequestMapping("/find")
    @UserLog(module = "用户管理", operate = "查询用户")
    public User findUser(String name){
        return userMapper.findByName(name);
    }


    @RequestMapping("/insertBatch/{size}")
    @UserLog(module = "用户管理", operate = "批量插入")
    public Boolean insertBatch(@PathVariable int size){

        int batchSize = 100;
        List<User> users = new ArrayList<>(batchSize);
        for (int i = 1; i <= size; i++) {
            String name = "zs" + i;
            User user = new User();
            user.setAge(i);
            user.setName(name);
            users.add(user);

            if (i % batchSize == 0){
                userMapper.insertBatch(users);
                users.clear();
            }
        }
        return Boolean.TRUE;
    }

}

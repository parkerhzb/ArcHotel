package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.UserMapper;
import com.arcsoft.hotel.pojo.User;
import com.arcsoft.hotel.pojo.UserExample;
import com.arcsoft.hotel.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getBySid(String sess_id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andSsesIdEqualTo(sess_id);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User getById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUsrByPhone(String phone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneNumberEqualTo(phone);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.get(0);
    }
}

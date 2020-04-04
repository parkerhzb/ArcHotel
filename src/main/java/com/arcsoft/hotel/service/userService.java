package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.User;

public interface userService {
    /**
     * 根据会话id获得当前登录用户信息
     *
     * @param sess_id 会话id
     * @return 用户信息
     */
    User getBySid(String sess_id);

    /**
     * 根据主键id查找
     *
     * @param id
     * @return
     */
    User getById(int id);

    /**
     * 2020/3/22
     * 根据phone获得一条user记录
     */
    User getUsrByPhone(String phone);
}

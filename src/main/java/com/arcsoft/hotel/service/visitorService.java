package com.arcsoft.hotel.service;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.Visitor;

import java.util.ArrayList;
import java.util.List;

public interface visitorService {
    int insertVisitor(Visitor visitor);

    /**
     * 根据邀请id查找对应的所有访客
     *
     * @param ivtId
     * @return
     */
    List<Visitor> getByIvtId(int ivtId);

    /**
     * 根据主键获取对应记录
     *
     * @param id
     * @return
     */
    Visitor getById(int id);

    /**
     * 根据主键查询访客权限
     *
     * @param id
     * @return
     */
    JSONObject getPermsById(int id);

    /**
     * 将数据库中存储的权限用json格式表示
     *
     * @param power
     * @return
     */
    JSONObject getPermsByPerm(String power);

    /**
     * 根据手机号查找访客记录，并保存邀请id到list中
     *
     * @param phone
     * @return
     */
    List<Integer> getIvtidByPhone(String phone);

    /**
     * 根据邀请id和手机号查找对应的访客
     *
     * @param ivtId
     * @param phone
     * @return
     */
    Visitor getByIvtidAndPhone(int ivtId, String phone);

    /**
     * 更新记录
     *
     * @param visitor
     * @return
     */
    boolean update(Visitor visitor);
}

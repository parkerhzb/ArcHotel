package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.Inperson;

import java.util.List;

public interface inpersonService {
    /**
     * 获得checkin_id在参数list中的所有记录
     *
     * @param checkinIds
     * @return
     */
    List<Inperson> getAllin(List checkinIds);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    Inperson getById(int id);

    /**
     * 根据电话号码查找
     *
     * @param phone
     * @return
     */
    Inperson getByPhoneNumber(String phone);

    /*
    2020/3/14
    获取所有未退房的人员信息
    */
    List<Inperson> getAllNotOut();

    /*
    2020/3/15
    根据checkin_id,标记退房
     */
    int is_check_out(int checkin_id);


    /**
     * 2020/3/26
     */
    int addInperson(Inperson inperson);
}

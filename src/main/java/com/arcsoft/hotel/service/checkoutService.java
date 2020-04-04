package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.CheckOut;

import java.util.List;

public interface checkoutService {
    List<CheckOut> getCheckoutByTypeANDNumber(Integer type, String number);

    CheckOut getCheckoutByCheckinid(int checkinId);

    double getConsumeByCheckinid(int checkinId);

    /*
  2020/3/15
  插入一条新记录
   */
    int addItem(CheckOut checkOut);

    /**
     * 2020/3/15
     * 根据checkin_id返回一条记录
     */
    CheckOut getByCheckin_id(int checkin_id);
}

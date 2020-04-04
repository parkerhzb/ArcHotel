package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.CheckOutMapper;
import com.arcsoft.hotel.pojo.CheckOut;
import com.arcsoft.hotel.pojo.CheckOutExample;
import com.arcsoft.hotel.service.checkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements checkoutService {
    @Autowired
    CheckOutMapper checkOutMapper;


    @Override
    public List<CheckOut> getCheckoutByTypeANDNumber(Integer type, String number) {
        CheckOutExample checkOutExample = new CheckOutExample();
        //checkOutExample.createCriteria().andDocumentTypeEqualTo(type).andDocumentNumberEqualTo(number);
        List<CheckOut> checkOuts = checkOutMapper.selectByExample(checkOutExample);
        return checkOuts;
    }

    @Override
    public CheckOut getCheckoutByCheckinid(int checkinId) {
        CheckOutExample checkOutExample = new CheckOutExample();
        checkOutExample.createCriteria().andCheckinIdEqualTo(checkinId);
        List<CheckOut> checkOuts = checkOutMapper.selectByExample(checkOutExample);
        if (checkOuts != null || checkOuts.size() != 0)
            return checkOuts.get(0);
        return null;
    }

    @Override
    public double getConsumeByCheckinid(int checkinId) {
        CheckOutExample checkOutExample = new CheckOutExample();
        checkOutExample.createCriteria().andCheckinIdEqualTo(checkinId);
        List<CheckOut> checkOuts = checkOutMapper.selectByExample(checkOutExample);
        if (checkOuts != null || checkOuts.size() != 0)
            return checkOuts.get(0).getPrice();
        return 0;
    }

    @Override
    public int addItem(CheckOut checkOut) {
        int re = checkOutMapper.insertSelective(checkOut);
        return re;
    }

    @Override
    public CheckOut getByCheckin_id(int checkin_id) {
        CheckOutExample checkOutExample = new CheckOutExample();
        checkOutExample.createCriteria().andCheckinIdEqualTo(checkin_id);
        CheckOut checkOut = checkOutMapper.selectByExample(checkOutExample).get(0);
        return checkOut;
    }
}

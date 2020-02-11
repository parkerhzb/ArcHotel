package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.CheckOutMapper;
import com.arcsoft.hotel.pojo.CheckOut;
import com.arcsoft.hotel.pojo.CheckOutExample;
import com.arcsoft.hotel.service.checkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class checkouServiceImpl implements checkoutService {
    @Autowired
    CheckOutMapper checkOutMapper;
    CheckOutExample checkOutExample = new CheckOutExample();

    @Override
    public List<CheckOut> getCheckoutByTypeANDNumber(Integer type, String number) {
        checkOutExample.createCriteria().andDocumentTypeEqualTo(type).andDocumentNumberEqualTo(number);
        List<CheckOut> checkOuts = checkOutMapper.selectByExample(checkOutExample);
        return checkOuts;
    }
}

package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.CheckOut;

import java.util.List;

public interface checkoutService {
    List<CheckOut> getCheckoutByTypeANDNumber(Integer type, String number);
}

package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.OutpersonMapper;
import com.arcsoft.hotel.pojo.Outperson;
import com.arcsoft.hotel.service.outpersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class outpersonServiceImpl implements outpersonService {
    @Autowired
    OutpersonMapper outpersonMapper;

    @Override
    public int addItem(Outperson outperson) {
        int re = outpersonMapper.insertSelective(outperson);
        return re;
    }
}

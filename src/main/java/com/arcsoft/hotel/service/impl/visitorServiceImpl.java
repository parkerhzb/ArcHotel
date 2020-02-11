package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.VisitorMapper;
import com.arcsoft.hotel.pojo.Visitor;
import com.arcsoft.hotel.pojo.VisitorExample;
import com.arcsoft.hotel.service.visitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class visitorServiceImpl implements visitorService {
    @Autowired
    VisitorMapper visitorMapper;
    VisitorExample visitorExample = new VisitorExample();

    @Override
    public int insertVisitor(Visitor visitor) {
        visitorMapper.insertSelective(visitor);
        return 0;
    }
}

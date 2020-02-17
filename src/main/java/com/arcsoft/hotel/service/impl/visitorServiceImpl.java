package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.VisitorMapper;
import com.arcsoft.hotel.pojo.Visitor;
import com.arcsoft.hotel.pojo.VisitorExample;
import com.arcsoft.hotel.service.visitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class visitorServiceImpl implements visitorService {
    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public int insertVisitor(Visitor visitor) {
        int re = visitorMapper.insertSelective(visitor);
        return re;
    }

    @Override
    public ArrayList<byte[]> getDoorFace(int roomId) {
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria().andRoomIdEqualTo(roomId);
        List<Visitor> visitors = visitorMapper.selectByExample(visitorExample);
        ArrayList<byte[]> face = new ArrayList<>();
        for (Visitor visitor : visitors) {
            //可用电梯为1，可进房间为2。传参数的时候传0或1或2或3
            if (visitor.getPower() == "2" || visitor.getPower() == "3")
                face.add(visitor.getFace());
        }
        return face;
    }
}

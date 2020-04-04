package com.arcsoft.hotel.service.impl;

import com.alibaba.fastjson.JSONObject;
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
    public List<Visitor> getByIvtId(int ivtId) {
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria().andInvitationIdEqualTo(ivtId);
        List<Visitor> visitors = visitorMapper.selectByExample(visitorExample);
        return visitors;
    }

    @Override
    public Visitor getById(int id) {
        return visitorMapper.selectByPrimaryKey(id);
    }

    @Override
    public JSONObject getPermsById(int id) {
        JSONObject result = new JSONObject(true);
        Visitor visitor = getById(id);
        if (visitor != null) {
            result = getPermsByPerm(visitor.getPower());
        }
        return result;
    }

    @Override
    public JSONObject getPermsByPerm(String power) {
        JSONObject result = new JSONObject(true);
        //String power=visitor.getPower();
        if (power.equals("3")) {
            result.put("lift", 1);
            result.put("room", 1);
        } else if (power.equals("2")) {
            result.put("lift", 0);
            result.put("room", 1);
        } else if (power.equals("1")) {
            result.put("lift", 1);
            result.put("room", 0);
        } else if (power.equals("0")) {
            result.put("lift", 0);
            result.put("room", 0);
        }
        return result;
    }

    @Override
    public List<Integer> getIvtidByPhone(String phone) {
        List<Integer> ivtIds = new ArrayList<Integer>();
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria().andPhoneNumberEqualTo(phone);
        List<Visitor> visitors = visitorMapper.selectByExample(visitorExample);
        if (visitors != null) {
            for (Visitor visitor : visitors)
                ivtIds.add(visitor.getInvitationId());
        }
        return ivtIds;
    }

    @Override
    public Visitor getByIvtidAndPhone(int ivtId, String phone) {
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria().andInvitationIdEqualTo(ivtId);
        visitorExample.createCriteria().andPhoneNumberEqualTo(phone);
        List<Visitor> visitors = visitorMapper.selectByExample(visitorExample);
        if (visitors != null && visitors.size() != 0)
            return visitors.get(0);
        return null;
    }

    @Override
    public boolean update(Visitor visitor) {
        return visitorMapper.updateByPrimaryKeySelective(visitor) > 0 ? true : false;
    }
}

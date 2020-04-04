package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.InpersonMapper;
import com.arcsoft.hotel.pojo.Inperson;
import com.arcsoft.hotel.pojo.InpersonExample;
import com.arcsoft.hotel.service.inpersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class inpersonServiceImpl implements inpersonService {
    @Autowired
    private InpersonMapper inpersonMapper;

    @Override
    public List<Inperson> getAllin(List checkinIds) {
        InpersonExample inpersonExample = new InpersonExample();
        inpersonExample.createCriteria().andCheckinIdIn(checkinIds);
        List inpersons = inpersonMapper.selectByExampleWithBLOBs(inpersonExample);
        return inpersons;
    }

    @Override
    public Inperson getById(int id) {
        return inpersonMapper.selectByPrimaryKey(id);
    }

    @Override
    public Inperson getByPhoneNumber(String phone) {
        InpersonExample inpersonExample = new InpersonExample();
        inpersonExample.createCriteria().andPhoneNumberEqualTo(phone);
        List<Inperson> inpersons = inpersonMapper.selectByExampleWithBLOBs(inpersonExample);
        if (inpersons != null && inpersons.size() != 0)
            return inpersons.get(0);
        return null;
    }

    @Override
    public List<Inperson> getAllNotOut() {
        InpersonExample inpersonExample = new InpersonExample();
        inpersonExample.createCriteria().andIsCheckOutIsNull();
        List<Inperson> result = inpersonMapper.selectByExampleWithBLOBs(inpersonExample);
        return result;
    }

    @Override
    public int is_check_out(int checkin_id) {
        Inperson inperson = new Inperson();
        inperson.setCheckinId(checkin_id);
        inperson.setIsCheckOut(Byte.valueOf("1"));
        InpersonExample inpersonExample = new InpersonExample();
        inpersonExample.createCriteria().andCheckinIdEqualTo(checkin_id);
        int re = inpersonMapper.updateByExampleSelective(inperson, inpersonExample);
        return re;
    }

    @Override
    public int addInperson(Inperson inperson) {
        int re = inpersonMapper.insertSelective(inperson);
        return re;
    }
}

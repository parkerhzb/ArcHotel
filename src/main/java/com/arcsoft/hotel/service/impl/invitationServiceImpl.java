package com.arcsoft.hotel.service.impl;

import com.arcsoft.hotel.mapper.InvitationMapper;
import com.arcsoft.hotel.pojo.*;
import com.arcsoft.hotel.service.checkinService;
import com.arcsoft.hotel.service.inpersonService;
import com.arcsoft.hotel.service.invitationService;
import com.arcsoft.hotel.util.DaysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class invitationServiceImpl implements invitationService {
    @Autowired
    private InvitationMapper invitationMapper;
    @Autowired
    private inpersonService inpersonService;
    @Autowired
    private checkinService checkinService;

    @Override
    public List<Invitation> getByUserid(int user_id) {
        InvitationExample invitationExample = new InvitationExample();
        invitationExample.createCriteria().andUserIdEqualTo(user_id);
        List<Invitation> invitations = invitationMapper.selectByExample(invitationExample);
        return invitations;
    }

    @Override
    public List<Invitation> getByRoomid(int room_id) {
        InvitationExample invitationExample = new InvitationExample();
        invitationExample.createCriteria().andRoomIdEqualTo(room_id);
        List<Invitation> invitations = invitationMapper.selectByExample(invitationExample);
        return invitations;
    }

    @Override
    public boolean isValid(int id) throws ParseException {
        Invitation invitation = invitationMapper.selectByPrimaryKey(id);
        return DaysUtil.isUnder24h(invitation.getTime(), new Date());
    }

    @Override
    public List<Invitation> getByIdIn(List<Integer> ivtIds) {
        if (ivtIds == null || ivtIds.size() == 0)
            return null;
        InvitationExample invitationExample = new InvitationExample();
        invitationExample.createCriteria().andIdIn(ivtIds);
        List<Invitation> invitations = invitationMapper.selectByExample(invitationExample);
        return invitations;
    }

    @Override
    public int getRoomidByUser(User user) {
        Inperson inperson = inpersonService.getByPhoneNumber(user.getPhoneNumber());
        if (inperson != null) {
            CheckIn checkIn = checkinService.getById(inperson.getCheckinId());
            if (checkIn != null)
                return checkIn.getRoomId();
        }
        return -1;
    }

    @Override
    public boolean insertInvitation(Invitation invitation) {
        return invitationMapper.insertSelective(invitation) > 0 ? true : false;
    }

    @Override
    public Invitation getByIvtcode(String ivtCode) {
        InvitationExample invitationExample = new InvitationExample();
        invitationExample.createCriteria().andInviteCodeEqualTo(ivtCode);
        List<Invitation> invitations = invitationMapper.selectByExample(invitationExample);
        if (invitations != null && invitations.size() != 0)
            return invitations.get(0);
        return null;
    }
}

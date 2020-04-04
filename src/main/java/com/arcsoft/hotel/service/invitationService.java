package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.Invitation;
import com.arcsoft.hotel.pojo.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface invitationService {
    /**
     * 根据用户id查找记录
     *
     * @param user_id
     * @return
     */
    List<Invitation> getByUserid(int user_id);

    /**
     * 根据房间id查找对应的邀请记录
     *
     * @param room_id
     * @return
     */
    List<Invitation> getByRoomid(int room_id);

    /**
     * 判断当前邀请是否有效
     *
     * @param id
     * @return
     * @throws ParseException
     */
    boolean isValid(int id) throws ParseException;

    /**
     * 根据id集合查找对应记录
     *
     * @param ivtIds
     * @return
     */
    List<Invitation> getByIdIn(List<Integer> ivtIds);

    /**
     * 根据user对象获取房间id
     *
     * @param user
     * @return
     */
    int getRoomidByUser(User user);

    /**
     * 插入新邀请
     *
     * @param invitation
     * @return
     */
    boolean insertInvitation(Invitation invitation);

    /**
     * 根据标识符获得邀请信息
     *
     * @param ivtCode
     * @return
     */
    Invitation getByIvtcode(String ivtCode);
}

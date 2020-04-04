package com.arcsoft.hotel.pojo;

import java.util.Date;

public class CheckIn {
    private Integer id;

    private Date checkinDate;

    private Date checkoutDate;

    private String time;

    private Integer roomId;

    private String relatedRoom;

    private Integer personNum;

    private Byte isCheckOut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRelatedRoom() {
        return relatedRoom;
    }

    public void setRelatedRoom(String relatedRoom) {
        this.relatedRoom = relatedRoom;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Byte getIsCheckOut() {
        return isCheckOut;
    }

    public void setIsCheckOut(Byte isCheckOut) {
        this.isCheckOut = isCheckOut;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", checkinDate=").append(checkinDate);
        sb.append(", checkoutDate=").append(checkoutDate);
        sb.append(", time=").append(time);
        sb.append(", roomId=").append(roomId);
        sb.append(", relatedRoom=").append(relatedRoom);
        sb.append(", personNum=").append(personNum);
        sb.append(", isCheckOut=").append(isCheckOut);
        sb.append("]");
        return sb.toString();
    }
}
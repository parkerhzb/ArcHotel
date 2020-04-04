package com.arcsoft.hotel.pojo;

import java.util.Date;

public class UserReserve {
    private Integer id;

    private String name;

    private Date checkinDate;

    private Date checkoutDate;

    private String roomType;

    private Date time;

    private String phoneNumber;

    private Integer num;

    private Integer userId;

    private Integer status;

    private Integer checkinId;

    private byte[] face;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Integer checkinId) {
        this.checkinId = checkinId;
    }

    public byte[] getFace() {
        return face;
    }

    public void setFace(byte[] face) {
        this.face = face;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", checkinDate=").append(checkinDate);
        sb.append(", checkoutDate=").append(checkoutDate);
        sb.append(", roomType=").append(roomType);
        sb.append(", time=").append(time);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", num=").append(num);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", checkinId=").append(checkinId);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
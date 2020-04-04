package com.arcsoft.hotel.pojo;

public class MeetingReserve {
    private Integer id;

    private String meetingType;

    private String name;

    private String phoneNumber;

    private String inviteCode;

    private Integer userId;

    private Integer status;

    private Integer meetingId;

    private byte[] face;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
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

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
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
        sb.append(", meetingType=").append(meetingType);
        sb.append(", name=").append(name);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", inviteCode=").append(inviteCode);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", meetingId=").append(meetingId);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
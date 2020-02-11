package com.arcsoft.hotel.pojo;

import java.util.Date;

public class CheckIn {
    private Integer id;

    private String name;

    private Integer documentType;

    private String documentNumber;

    private String gender;

    private Date checkinDate;

    private Date checkoutDate;

    private String time;

    private Integer roomId;

    private String relatedRoom;

    private Byte isCheckOut;

    private String phoneNumber;

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

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Byte getIsCheckOut() {
        return isCheckOut;
    }

    public void setIsCheckOut(Byte isCheckOut) {
        this.isCheckOut = isCheckOut;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        sb.append(", documentType=").append(documentType);
        sb.append(", documentNumber=").append(documentNumber);
        sb.append(", gender=").append(gender);
        sb.append(", checkinDate=").append(checkinDate);
        sb.append(", checkoutDate=").append(checkoutDate);
        sb.append(", time=").append(time);
        sb.append(", roomId=").append(roomId);
        sb.append(", relatedRoom=").append(relatedRoom);
        sb.append(", isCheckOut=").append(isCheckOut);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
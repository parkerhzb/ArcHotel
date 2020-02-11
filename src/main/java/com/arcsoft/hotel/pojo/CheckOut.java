package com.arcsoft.hotel.pojo;

import java.util.Date;

public class CheckOut {
    private Integer id;

    private Integer roomId;

    private Double price;

    private Date checkinDate;

    private Date checkoutDate;

    private Date time;

    private String name;

    private Integer documentType;

    private String documentNumber;

    private Integer checkinId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public Integer getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Integer checkinId) {
        this.checkinId = checkinId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomId=").append(roomId);
        sb.append(", price=").append(price);
        sb.append(", checkinDate=").append(checkinDate);
        sb.append(", checkoutDate=").append(checkoutDate);
        sb.append(", time=").append(time);
        sb.append(", name=").append(name);
        sb.append(", documentType=").append(documentType);
        sb.append(", documentNumber=").append(documentNumber);
        sb.append(", checkinId=").append(checkinId);
        sb.append("]");
        return sb.toString();
    }
}
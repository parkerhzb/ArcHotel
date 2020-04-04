package com.arcsoft.hotel.pojo;

import java.util.Date;

public class Park {
    private Integer id;

    private String carNumber;

    private Date parkTime;

    private Date leaveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getParkTime() {
        return parkTime;
    }

    public void setParkTime(Date parkTime) {
        this.parkTime = parkTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", carNumber=").append(carNumber);
        sb.append(", parkTime=").append(parkTime);
        sb.append(", leaveTime=").append(leaveTime);
        sb.append("]");
        return sb.toString();
    }
}
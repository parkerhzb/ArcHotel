package com.arcsoft.hotel.pojo;

import java.util.Date;

public class MeetingDate {
    private Integer id;

    private Integer reserveId;

    private Date date;

    private Integer timeperiod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTimeperiod() {
        return timeperiod;
    }

    public void setTimeperiod(Integer timeperiod) {
        this.timeperiod = timeperiod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reserveId=").append(reserveId);
        sb.append(", date=").append(date);
        sb.append(", timeperiod=").append(timeperiod);
        sb.append("]");
        return sb.toString();
    }
}
package com.arcsoft.hotel.pojo;

import java.util.Date;

public class CheckOut {
    private Integer id;

    private Integer checkinId;

    private Double price;

    private Date checkoutDate;

    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Integer checkinId) {
        this.checkinId = checkinId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", checkinId=").append(checkinId);
        sb.append(", price=").append(price);
        sb.append(", checkoutDate=").append(checkoutDate);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}
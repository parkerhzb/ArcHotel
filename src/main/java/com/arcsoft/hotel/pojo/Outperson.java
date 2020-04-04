package com.arcsoft.hotel.pojo;

public class Outperson {
    private Integer id;

    private Integer checkinId;

    private Integer checkoutId;

    private Integer inpersonId;

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

    public Integer getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(Integer checkoutId) {
        this.checkoutId = checkoutId;
    }

    public Integer getInpersonId() {
        return inpersonId;
    }

    public void setInpersonId(Integer inpersonId) {
        this.inpersonId = inpersonId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", checkinId=").append(checkinId);
        sb.append(", checkoutId=").append(checkoutId);
        sb.append(", inpersonId=").append(inpersonId);
        sb.append("]");
        return sb.toString();
    }
}
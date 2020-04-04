package com.arcsoft.hotel.pojo;

public class Car {
    private Integer id;

    private String carNumber;

    private Byte isCheckin;

    private Integer flag;

    private byte[] face;

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

    public Byte getIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(Byte isCheckin) {
        this.isCheckin = isCheckin;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
        sb.append(", carNumber=").append(carNumber);
        sb.append(", isCheckin=").append(isCheckin);
        sb.append(", flag=").append(flag);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
package com.arcsoft.hotel.pojo;

public class Car {
    private Integer id;

    private Byte isCheckin;

    private byte[] face;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(Byte isCheckin) {
        this.isCheckin = isCheckin;
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
        sb.append(", isCheckin=").append(isCheckin);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
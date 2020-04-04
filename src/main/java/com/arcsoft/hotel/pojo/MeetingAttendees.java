package com.arcsoft.hotel.pojo;

public class MeetingAttendees {
    private Integer id;

    private Integer reserveId;

    private String name;

    private Integer status;

    private byte[] face;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", reserveId=").append(reserveId);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
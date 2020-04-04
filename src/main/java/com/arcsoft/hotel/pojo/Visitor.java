package com.arcsoft.hotel.pojo;

public class Visitor {
    private Integer id;

    private String name;

    private String phoneNumber;

    private Integer invitationId;

    private String power;

    private Integer documentType;

    private String documentNumber;

    private String faceurl;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
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

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
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
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", invitationId=").append(invitationId);
        sb.append(", power=").append(power);
        sb.append(", documentType=").append(documentType);
        sb.append(", documentNumber=").append(documentNumber);
        sb.append(", faceurl=").append(faceurl);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
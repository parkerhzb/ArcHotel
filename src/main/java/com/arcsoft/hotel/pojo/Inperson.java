package com.arcsoft.hotel.pojo;

public class Inperson {
    private Integer id;

    private Integer checkinId;

    private String name;

    private Integer documentType;

    private String documentNumber;

    private String phoneNumber;

    private String faceurl;

    private Byte isCheckOut;

    private byte[] face;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
    }

    public Byte getIsCheckOut() {
        return isCheckOut;
    }

    public void setIsCheckOut(Byte isCheckOut) {
        this.isCheckOut = isCheckOut;
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
        sb.append(", checkinId=").append(checkinId);
        sb.append(", name=").append(name);
        sb.append(", documentType=").append(documentType);
        sb.append(", documentNumber=").append(documentNumber);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", faceurl=").append(faceurl);
        sb.append(", isCheckOut=").append(isCheckOut);
        sb.append(", face=").append(face);
        sb.append("]");
        return sb.toString();
    }
}
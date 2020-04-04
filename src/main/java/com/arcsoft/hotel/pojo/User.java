package com.arcsoft.hotel.pojo;

public class User {
    private Integer id;

    private String status;

    private String openId;

    private String ssesId;

    private String nickname;

    private String headimgurl;

    private String phoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSsesId() {
        return ssesId;
    }

    public void setSsesId(String ssesId) {
        this.ssesId = ssesId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", status=").append(status);
        sb.append(", openId=").append(openId);
        sb.append(", ssesId=").append(ssesId);
        sb.append(", nickname=").append(nickname);
        sb.append(", headimgurl=").append(headimgurl);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append("]");
        return sb.toString();
    }
}
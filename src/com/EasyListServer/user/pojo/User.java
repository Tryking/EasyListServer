package com.EasyListServer.user.pojo;

import java.util.Date;

public class User {
    private Long id;

    private String memberid;

    private String account;

    private String password;

    private Integer gender;

    private String phone;

    private String qq;

    private String qqname;

    private String sina;

    private String sinaname;

    private String signature;

    private Date createdtime;

    private Date updatedtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getQqname() {
        return qqname;
    }

    public void setQqname(String qqname) {
        this.qqname = qqname == null ? null : qqname.trim();
    }

    public String getSina() {
        return sina;
    }

    public void setSina(String sina) {
        this.sina = sina == null ? null : sina.trim();
    }

    public String getSinaname() {
        return sinaname;
    }

    public void setSinaname(String sinaname) {
        this.sinaname = sinaname == null ? null : sinaname.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(Date updatedtime) {
        this.updatedtime = updatedtime;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", memberid=" + memberid + ", account=" + account + ", password=" + password
				+ ", gender=" + gender + ", phone=" + phone + ", qq=" + qq + ", qqname=" + qqname + ", sina=" + sina
				+ ", sinaname=" + sinaname + ", signature=" + signature + ", createdtime=" + createdtime
				+ ", updatedtime=" + updatedtime + "]";
	} 
}
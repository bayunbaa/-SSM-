package com.xh.entity.login;

public class Worker {
    private Integer uid;

    private String uname;

    private String upassword;

    private String creatertime;

    private String updatetime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public String getCreatertime() {
        return creatertime;
    }

    public void setCreatertime(String creatertime) {
        this.creatertime = creatertime == null ? null : creatertime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }
}
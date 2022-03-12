package com.xh.entity;

public class Inform {
    private Integer iid;

    private String ibody;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getIbody() {
        return ibody;
    }

    public void setIbody(String ibody) {
        this.ibody = ibody == null ? null : ibody.trim();
    }
}
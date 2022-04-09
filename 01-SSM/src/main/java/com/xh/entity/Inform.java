package com.xh.entity;

public class Inform {
    private Integer iid;

    private Integer uid;

    private String ibody;

    private Integer itype;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getIbody() {
        return ibody;
    }

    public void setIbody(String ibody) {
        this.ibody = ibody == null ? null : ibody.trim();
    }

    public Integer getItype() {
        return itype;
    }

    public void setItype(Integer itype) {
        this.itype = itype;
    }
}
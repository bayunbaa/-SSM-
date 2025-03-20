package com.xh.entity;

public class Delivery {
    private Integer id;

    private Integer dtid;

    private String dname;

    private String dtime;

    private Integer dnum;
    private Integer dnum2;

    private Integer dtype;

    private String dfctory;

    private String dtrange;

    private Integer dcollege;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDtid() {
        return dtid;
    }

    public void setDtid(Integer dtid) {
        this.dtid = dtid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime == null ? null : dtime.trim();
    }

    public Integer getDnum() {
        return dnum;
    }

    public void setDnum(Integer dnum) {
        this.dnum = dnum;
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public String getDfctory() {
        return dfctory;
    }

    public void setDfctory(String dfctory) {
        this.dfctory = dfctory == null ? null : dfctory.trim();
    }

    public String getDtrange() {
        return dtrange;
    }

    public void setDtrange(String dtrange) {
        this.dtrange = dtrange == null ? null : dtrange.trim();
    }

    public Integer getDcollege() {
        return dcollege;
    }

    public void setDcollege(Integer dcollege) {
        this.dcollege = dcollege;
    }

    public Integer getDnum2() {
        return dnum2;
    }

    public void setDnum2(Integer dnum2) {
        this.dnum2 = dnum2;
    }
}
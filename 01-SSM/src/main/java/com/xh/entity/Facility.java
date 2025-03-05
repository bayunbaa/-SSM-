package com.xh.entity;



public class Facility {
    private Integer id;


    private Integer ftid;

    private String fname;
    private String fgrade;

    private String ftime;
    private String location;

    private Integer fnum;
    private Integer fnum2;

    private Integer ftype;

    private String ffctory;

    private String ftrange;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFtid() {
        return ftid;
    }

    public void setFtid(Integer ftid) {
        this.ftid = ftid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime == null ? null : ftime.trim();
    }

    public Integer getFnum() {
        return fnum;
    }

    public void setFnum(Integer fnum) {
        this.fnum = fnum;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public String getFfctory() {
        return ffctory;
    }

    public void setFfctory(String ffctory) {
        this.ffctory = ffctory == null ? null : ffctory.trim();
    }

    public String getFtrange() {
        return ftrange;
    }

    public void setFtrange(String ftrange) {
        this.ftrange = ftrange == null ? null : ftrange.trim();
    }
    public String getFgrade() {
        return fgrade;
    }

    public void setFgrade(String fgrade) {
        this.fgrade = fgrade;
    }


    public Integer getFnum2() {
        return fnum2;
    }

    public void setFnum2(Integer fnum2) {
        this.fnum2 = fnum2;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", ftid=" + ftid +
                ", fname='" + fname + '\'' +
                ", fgrade='" + fgrade + '\'' +
                ", ftime='" + ftime + '\'' +
                ", fnum=" + fnum +
                ", ftype=" + ftype +
                ", ffctory='" + ffctory + '\'' +
                ", ftrange='" + ftrange + '\'' +
                '}';
    }
}
package com.tnt.fund.analyse.service.entity;

public class FundValue extends BaseEntity {

    private static final long serialVersionUID = 3428132375182515631L;

    // 主键
    private Long id;

    // 基金代码
    private String fundCode;

    // 日期
    private String fsrq;

    // 单位净值
    private String dwjz;

    // 累计净值
    private String ljjz;

    // 日增长率
    private String rzzl;

    // 申购状态
    private String sgzt;

    // 分红送配
    private String fhsp;

    // 赎回状态
    private String shzt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFsrq() {
        return fsrq;
    }

    public void setFsrq(String fsrq) {
        this.fsrq = fsrq;
    }

    public String getDwjz() {
        return dwjz;
    }

    public void setDwjz(String dwjz) {
        this.dwjz = dwjz;
    }

    public String getLjjz() {
        return ljjz;
    }

    public void setLjjz(String ljjz) {
        this.ljjz = ljjz;
    }

    public String getRzzl() {
        return rzzl;
    }

    public void setRzzl(String rzzl) {
        this.rzzl = rzzl;
    }

    public String getSgzt() {
        return sgzt;
    }

    public void setSgzt(String sgzt) {
        this.sgzt = sgzt;
    }

    public String getFhsp() {
        return fhsp;
    }

    public void setFhsp(String fhsp) {
        this.fhsp = fhsp;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

}

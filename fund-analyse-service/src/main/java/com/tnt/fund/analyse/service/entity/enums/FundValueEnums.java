package com.tnt.fund.analyse.service.entity.enums;

public enum FundValueEnums {

    // 申购状态
    ShengouStatus_Fengbi("封闭期", "-1"),
    ShengouStatus_Normal("开放申购", "1"),
    ShengouStatus_Stop("暂停申购", "-2"),

    // 赎回状态
    ShuhuiStatus_Fengbi("封闭期", "-1"),
    ShuhuiStatus_Normal("开放赎回", "1"),
    ShuhuiStatus_Stop("暂停赎回", "-2");

    private String name;
    private String status;

    private FundValueEnums(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

package com.tnt.fund.analyse.service.entity;

public class FundCode extends BaseEntity {

    private static final long serialVersionUID = -408907091070595944L;

    // 基金代码
    private String fundCode;

    // 基金名称
    private String fundName;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

}

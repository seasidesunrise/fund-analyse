package com.tnt.fund.analyse.service.dao.impl;

import com.tnt.fund.analyse.service.dao.FundCodeDAO;
import com.tnt.fund.analyse.service.entity.FundCode;
import org.springframework.stereotype.Repository;

@Repository("fundCodeDAO")
public class FundCodeDAOImpl extends BaseDAOMybatis<FundCode, String> implements FundCodeDAO {
}

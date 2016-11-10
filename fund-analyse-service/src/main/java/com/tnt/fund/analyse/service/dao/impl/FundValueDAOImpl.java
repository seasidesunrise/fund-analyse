package com.tnt.fund.analyse.service.dao.impl;

import com.tnt.fund.analyse.service.dao.FundValueDAO;
import com.tnt.fund.analyse.service.entity.FundValue;
import org.springframework.stereotype.Repository;

@Repository("fundValueDAO")
public class FundValueDAOImpl extends BaseDAOMybatis<FundValue, Long> implements FundValueDAO {
}

package com.tnt.fund.analyse.service.service.impl;

import com.tnt.fund.analyse.service.dao.BaseDAO;
import com.tnt.fund.analyse.service.dao.FundCodeDAO;
import com.tnt.fund.analyse.service.dao.FundValueDAO;
import com.tnt.fund.analyse.service.entity.FundValue;
import com.tnt.fund.analyse.service.service.FundValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fundValueService")
public class FundValueServiceImpl extends BaseServiceImpl<FundValue, Long> implements FundValueService {

    @Autowired
    private FundValueDAO fundValueDAO;

    @Override
    protected BaseDAO<FundValue, Long> getBaseDao() {
        return fundValueDAO;
    }

}

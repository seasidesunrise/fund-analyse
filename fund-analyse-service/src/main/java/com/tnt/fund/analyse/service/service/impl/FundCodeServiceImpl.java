package com.tnt.fund.analyse.service.service.impl;

import com.tnt.fund.analyse.service.dao.BaseDAO;
import com.tnt.fund.analyse.service.dao.FundCodeDAO;
import com.tnt.fund.analyse.service.entity.FundCode;
import com.tnt.fund.analyse.service.service.FundCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fundCodeService")
public class FundCodeServiceImpl extends BaseServiceImpl<FundCode, String> implements FundCodeService {

    @Autowired
    private FundCodeDAO fundCodeDAO;

    @Override
    protected BaseDAO<FundCode, String> getBaseDao() {
        return fundCodeDAO;
    }

}

package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.FinanceMapper;
import com.hao.app.pojo.FinanceDO;
import com.hao.app.service.FinanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private FinanceMapper financeMapper;


    @Override
    public JsonResult<FinanceDO> searchFinance() {
        return null;
    }

    @Override
    public FinanceDO getById(int id) {
        return null;
    }

    @Override
    public ResultCodeEnum insert(FinanceDO finance) {
        return null;
    }

    @Override
    public ResultCodeEnum update(FinanceDO finance) {
        return null;
    }
}

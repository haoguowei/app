package com.hao.app.service;

import com.hao.app.commons.entity.param.FinanceQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.FinanceDO;

public interface FinanceService {

    JsonResult<FinanceDO> searchFinance(FinanceQueryParam param);

    FinanceDO getById(int id);

    ResultCodeEnum insert(FinanceDO finance);

    ResultCodeEnum update(FinanceDO finance);

    String searchFinance4HJ(FinanceQueryParam param);
}

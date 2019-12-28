package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.FinanceDO;

public interface FinanceService {

    JsonResult<FinanceDO> searchFinance(Integer projectsId, String fromDay, String endDay);

    FinanceDO getById(int id);

    ResultCodeEnum insert(FinanceDO finance);

    ResultCodeEnum update(FinanceDO finance);
}

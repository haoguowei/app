package com.hao.app.service;

import com.hao.app.commons.entity.param.IncomeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.IncomeDO;


public interface IncomeService {

    JsonResult<IncomeDO> search(IncomeQueryParam param);

    IncomeDO getById(int id);

    ResultCodeEnum insert(IncomeDO cost);

    ResultCodeEnum update(IncomeDO cost);
}

package com.hao.app.service;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.CostsDO;

public interface CostsService {

    JsonResult<CostsDO> searchCosts(CostQueryParam param);

    CostsDO getById(int id);

    ResultCodeEnum insert(CostsDO cost);

    ResultCodeEnum update(CostsDO cost);

}

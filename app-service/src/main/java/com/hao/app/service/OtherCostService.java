package com.hao.app.service;

import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.OtherCostDO;

public interface OtherCostService {

    JsonResult<OtherCostDO> searchCost(OtherCostQueryParam param);

    OtherCostDO getById(int id);

    ResultCodeEnum insert(OtherCostDO cost);

    ResultCodeEnum update(OtherCostDO cost);

    String searchCost4HJ(OtherCostQueryParam param);
}

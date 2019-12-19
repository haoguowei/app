package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.YYCostDO;

public interface YYCostService {

    JsonResult<YYCostDO> searchYYCost();

    YYCostDO getById(int id);

    ResultCodeEnum insert(YYCostDO cost);

    ResultCodeEnum update(YYCostDO cost);
}

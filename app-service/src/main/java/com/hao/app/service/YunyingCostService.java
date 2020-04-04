package com.hao.app.service;

import com.hao.app.commons.entity.param.YunyingCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.YunyingCostDO;

import java.util.List;

public interface YunyingCostService {

    JsonResult<YunyingCostDO> searchCost(YunyingCostQueryParam param);

    YunyingCostDO getById(int id);

    ResultCodeEnum insert(YunyingCostDO cost);

    ResultCodeEnum update(YunyingCostDO cost);

    String searchCost4HJ(YunyingCostQueryParam param);

    List<ResultStatistics> searchTotalPay(YunyingCostQueryParam param);

    List<ResultStatistics> searchTotalPayForProjects(YunyingCostQueryParam param);
}

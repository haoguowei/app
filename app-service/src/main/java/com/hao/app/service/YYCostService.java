package com.hao.app.service;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.YYCostDO;

import java.math.BigDecimal;
import java.util.List;

public interface YYCostService {

    JsonResult<YYCostDO> searchYYCost(CostQueryParam param);

    YYCostDO getById(int id);

    ResultCodeEnum insert(YYCostDO cost);

    ResultCodeEnum update(YYCostDO cost);

    String searchYYCost4HJ(CostQueryParam param);

    BigDecimal searchTotalPay(CostQueryParam param);

    List<ResultStatistics> searchTotalPayForProjects(OtherCostQueryParam param);
}

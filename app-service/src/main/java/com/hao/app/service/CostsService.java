package com.hao.app.service;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.CostsDO;

import java.math.BigDecimal;
import java.util.List;

public interface CostsService {

    JsonResult<CostsDO> searchYYCost(CostQueryParam param);

    CostsDO getById(int id);

    ResultCodeEnum insert(CostsDO cost);

    ResultCodeEnum update(CostsDO cost);

    String searchYYCost4HJ(CostQueryParam param);

    BigDecimal searchTotalPay(CostQueryParam param);

    List<ResultStatistics> searchTotalPayForProjects(CostQueryParam param);
}

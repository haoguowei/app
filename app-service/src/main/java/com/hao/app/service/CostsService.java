package com.hao.app.service;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.AmountTable;
import com.hao.app.commons.entity.result.CostsTableMonth;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.CostsDO;
import com.hao.app.pojo.CostsTypeDO;

import java.util.List;
import java.util.Map;

public interface CostsService {

    Map<Integer, String> mapCostsType();

    List<CostsTypeDO> listCostsTypeByParentId(Integer parentId);

    JsonResult<CostsDO> searchCosts(CostQueryParam param);

    CostsDO getById(int id);

    ResultCodeEnum insert(CostsDO cost);

    ResultCodeEnum update(CostsDO cost);

    boolean updateStatus(int id);

    List<AmountTable> searchCostsTable(TableQueryParam param);

    List<CostsTableMonth> searchCostsTableMonth(TableQueryParam param);
}

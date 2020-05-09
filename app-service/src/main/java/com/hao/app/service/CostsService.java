package com.hao.app.service;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.CostsDO;
import com.hao.app.pojo.CostsTypeDO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CostsService {

    Map<TableKey, BigDecimal> getCostTable(TableQueryParam param);

    Map<Integer, String> mapCostsType();

    List<CostsTypeDO> getTableTypes();

    List<CostsTypeDO> listCostsTypeByParentId(Integer parentId);

    JsonResult<CostsDO> searchCosts(CostQueryParam param);

    CostsDO getById(int id);

    ResultCodeEnum insert(CostsDO cost);

    ResultCodeEnum update(CostsDO cost);

    boolean updateStatus(int id);

}

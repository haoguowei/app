package com.hao.app.service;

import com.hao.app.commons.entity.param.IncomeQueryParam;
import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.AmountTable;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.IncomeDO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface IncomeService {

    Map<TableKey, BigDecimal> getIncomeTable(TableQueryParam param);

    JsonResult<IncomeDO> search(IncomeQueryParam param);

    IncomeDO getById(int id);

    ResultCodeEnum insert(IncomeDO cost);

    ResultCodeEnum update(IncomeDO cost);

    Map<TableKey, BigDecimal> getIncomeTable2(TableQueryParam param);

    Map<TableKey, BigDecimal> getIncomeTableZhuanxiang(TableQueryParam param);

    List<AmountTable> getIncomeTableList2(TableQueryParam param);
}

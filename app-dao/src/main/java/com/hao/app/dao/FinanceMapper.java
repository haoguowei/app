package com.hao.app.dao;

import com.hao.app.commons.entity.param.FinanceQueryParam;
import com.hao.app.pojo.FinanceDO;

import java.util.List;

public interface FinanceMapper {

    int insert(FinanceDO record);

    FinanceDO selectByPrimaryKey(Integer id);

    int update(FinanceDO record);

    List<FinanceDO> search(FinanceQueryParam param);

    int count(FinanceQueryParam param);
}

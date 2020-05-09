package com.hao.app.dao;


import com.hao.app.commons.entity.param.IncomeQueryParam;
import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.AmountTable;
import com.hao.app.pojo.IncomeDO;

import java.util.List;

public interface IncomeMapper {

    int insert(IncomeDO record);

    IncomeDO selectByPrimaryKey(Integer id);

    int update(IncomeDO record);

    int count(IncomeQueryParam param);

    List<IncomeDO> search(IncomeQueryParam param);

    List<AmountTable> searchIncomeTable(TableQueryParam param);

}

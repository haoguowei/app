package com.hao.app.dao;

import com.hao.app.pojo.FinanceDO;

public interface FinanceMapper {
    
    int insert(FinanceDO record);

    FinanceDO selectByPrimaryKey(Integer id);

    int update(FinanceDO record);
}

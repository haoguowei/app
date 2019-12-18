package com.hao.app.dao;

import com.hao.app.pojo.FinanceDO;

public interface FinanceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(FinanceDO record);

    int insertSelective(FinanceDO record);

    FinanceDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceDO record);

    int updateByPrimaryKey(FinanceDO record);
}
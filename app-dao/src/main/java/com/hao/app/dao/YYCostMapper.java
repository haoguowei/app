package com.hao.app.dao;


import com.hao.app.pojo.YYCostDO;

public interface YYCostMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YYCostDO record);

    int insertSelective(YYCostDO record);

    YYCostDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YYCostDO record);

    int updateByPrimaryKey(YYCostDO record);
}
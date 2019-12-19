package com.hao.app.dao;


import com.hao.app.pojo.YYCostDO;

public interface YYCostMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YYCostDO record);

    YYCostDO selectByPrimaryKey(Integer id);

    int update(YYCostDO record);
}

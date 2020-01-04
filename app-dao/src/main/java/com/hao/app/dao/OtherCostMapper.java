package com.hao.app.dao;


import com.hao.app.pojo.OtherCostDO;

public interface OtherCostMapper {

    int insert(OtherCostDO record);

    OtherCostDO selectByPrimaryKey(Integer id);

    int update(OtherCostDO record);

}

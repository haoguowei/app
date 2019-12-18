package com.hao.app.dao;

import com.hao.app.pojo.AreaDO;

public interface AreaDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AreaDO record);

    int insertSelective(AreaDO record);

    AreaDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaDO record);

    int updateByPrimaryKey(AreaDO record);
}
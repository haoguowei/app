package com.hao.app.dao;

import com.hao.app.pojo.AreaDO;

import java.util.List;

public interface AreaMapper {

    int insert(AreaDO record);

    AreaDO selectByPrimaryKey(Integer id);

    int update(AreaDO record);

    List<AreaDO> searchAreas();
}
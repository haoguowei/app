package com.hao.app.dao;


import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.pojo.CostsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CostsDO record);

    CostsDO selectByPrimaryKey(Integer id);

    int update(CostsDO record);

    int count(CostQueryParam param);

    List<CostsDO> search(CostQueryParam param);

    int updateStatus(@Param("id") int id);
}

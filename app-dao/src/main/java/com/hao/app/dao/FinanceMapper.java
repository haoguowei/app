package com.hao.app.dao;

import com.hao.app.pojo.FinanceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceMapper {

    int insert(FinanceDO record);

    FinanceDO selectByPrimaryKey(Integer id);

    int update(FinanceDO record);

    List<FinanceDO> search(@Param("projectsId") Integer projectsId, @Param("fromDay") String fromDay, @Param("endDay") String endDay);
}

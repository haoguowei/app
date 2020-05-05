package com.hao.app.dao;


import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.pojo.CostsDO;

import java.util.List;
import java.util.Map;

public interface CostsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CostsDO record);

    CostsDO selectByPrimaryKey(Integer id);

    int update(CostsDO record);

    int count(CostQueryParam param);

    List<CostsDO> search(CostQueryParam param);

    Map<String, Object> searchHJ(CostQueryParam param);

    List<ResultStatistics> searchTotalPayForProjects(CostQueryParam param);
}

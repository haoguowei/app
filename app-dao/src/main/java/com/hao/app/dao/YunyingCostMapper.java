package com.hao.app.dao;


import com.hao.app.commons.entity.param.YunyingCostQueryParam;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.pojo.YunyingCostDO;

import java.util.List;
import java.util.Map;

public interface YunyingCostMapper {

    int insert(YunyingCostDO record);

    YunyingCostDO selectByPrimaryKey(Integer id);

    int update(YunyingCostDO record);

    int count(YunyingCostQueryParam param);

    List<YunyingCostDO> search(YunyingCostQueryParam param);

    Map<String, Object> searchHJ(YunyingCostQueryParam param);

    List<ResultStatistics> searchTotalPay(YunyingCostQueryParam param);

    List<ResultStatistics> searchTotalPayForProjects(YunyingCostQueryParam param);
}

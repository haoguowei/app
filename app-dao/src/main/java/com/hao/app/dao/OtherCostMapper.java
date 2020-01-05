package com.hao.app.dao;


import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.pojo.OtherCostDO;

import java.util.List;
import java.util.Map;

public interface OtherCostMapper {

    int insert(OtherCostDO record);

    OtherCostDO selectByPrimaryKey(Integer id);

    int update(OtherCostDO record);

    int count(OtherCostQueryParam param);

    List<OtherCostDO> search(OtherCostQueryParam param);

    Map<String, Object> searchHJ(OtherCostQueryParam param);

    List<ResultStatistics> searchTotalPay(OtherCostQueryParam param);
}

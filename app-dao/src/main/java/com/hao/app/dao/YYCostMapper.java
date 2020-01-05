package com.hao.app.dao;


import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.pojo.YYCostDO;

import java.util.List;
import java.util.Map;

public interface YYCostMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YYCostDO record);

    YYCostDO selectByPrimaryKey(Integer id);

    int update(YYCostDO record);

    int count(CostQueryParam param);

    List<YYCostDO> search(CostQueryParam param);

    Map<String, Object> searchHJ(CostQueryParam param);

}

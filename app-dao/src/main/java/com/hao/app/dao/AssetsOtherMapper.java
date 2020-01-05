package com.hao.app.dao;


import com.hao.app.commons.entity.param.AssetsOtherQueryParam;
import com.hao.app.pojo.AssetsOtherDO;

import java.util.List;
import java.util.Map;

public interface AssetsOtherMapper {

    int insert(AssetsOtherDO record);

    AssetsOtherDO selectByPrimaryKey(Integer id);

    int update(AssetsOtherDO record);

    int count(AssetsOtherQueryParam param);

    List<AssetsOtherDO> search(AssetsOtherQueryParam param);

    Map<String, Object> searchHJ(AssetsOtherQueryParam param);
}

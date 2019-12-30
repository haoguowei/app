package com.hao.app.dao;


import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.pojo.AssetsDO;

import java.util.List;
import java.util.Map;

public interface AssetsMapper {

    int insert(AssetsDO record);

    AssetsDO selectByPrimaryKey(Integer id);

    int update(AssetsDO record);

    int count(AssetsQueryParam param);

    List<AssetsDO> search(AssetsQueryParam param);

    Map<String, Object> searchHJ(AssetsQueryParam param);
}

package com.hao.app.dao;


import com.hao.app.pojo.AssetsDO;

public interface AssetsMapper {

    int insert(AssetsDO record);

    AssetsDO selectByPrimaryKey(Integer id);

    int update(AssetsDO record);
}

package com.hao.app.dao;


import com.hao.app.pojo.AssetsDO;

public interface AssetsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AssetsDO record);

    int insertSelective(AssetsDO record);

    AssetsDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetsDO record);

    int updateByPrimaryKey(AssetsDO record);
}
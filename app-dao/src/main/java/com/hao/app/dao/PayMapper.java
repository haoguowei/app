package com.hao.app.dao;


import com.hao.app.pojo.PayDO;

public interface PayMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PayDO record);

    PayDO selectByPrimaryKey(Integer id);

    int update(PayDO record);

}
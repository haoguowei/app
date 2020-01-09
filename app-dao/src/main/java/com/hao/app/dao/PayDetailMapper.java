package com.hao.app.dao;


import com.hao.app.pojo.PayDetailDO;

public interface PayDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PayDetailDO record);

    PayDetailDO selectByPrimaryKey(Integer id);

    int update(PayDetailDO record);
}
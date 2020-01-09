package com.hao.app.dao;


import com.hao.app.commons.entity.param.PayQueryParam;
import com.hao.app.pojo.PayDO;

import java.util.List;

public interface PayMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PayDO record);

    PayDO selectByPrimaryKey(Integer id);

    int update(PayDO record);

    int count(PayQueryParam param);

    List<PayDO> search(PayQueryParam param);
}
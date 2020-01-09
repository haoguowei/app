package com.hao.app.dao;


import com.hao.app.commons.entity.param.PayQueryParam;
import com.hao.app.pojo.PayDO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PayMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PayDO record);

    PayDO selectByPrimaryKey(Integer id);

    int update(PayDO record);

    int count(PayQueryParam param);

    List<PayDO> search(PayQueryParam param);

    int updateTotals(@Param("payId") int payId, @Param("totalMan") int totalMan, @Param("payStatus") int payStatus, @Param("totalAmount") BigDecimal totalAmount);
}
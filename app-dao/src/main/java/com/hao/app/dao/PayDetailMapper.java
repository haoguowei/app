package com.hao.app.dao;


import com.hao.app.pojo.PayDetailDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PayDetailDO record);

    PayDetailDO selectByPrimaryKey(Integer id);

    int update(PayDetailDO record);

    List<PayDetailDO> search(@Param("payId") Integer payId);
}
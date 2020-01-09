package com.hao.app.service;

import com.hao.app.commons.entity.param.PayQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.PayDO;

public interface PayService {

    ResultCodeEnum insert(PayDO item);

    ResultCodeEnum update(PayDO item);

    PayDO getById(Integer id);

    JsonResult<PayDO> searchPay(PayQueryParam param);
}

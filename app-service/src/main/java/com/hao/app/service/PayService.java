package com.hao.app.service;

import com.hao.app.commons.entity.param.PayQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.PayDO;
import com.hao.app.pojo.PayDetailDO;

public interface PayService {

    ResultCodeEnum insert(PayDO item);

    ResultCodeEnum update(PayDO item);

    PayDO getById(Integer id);

    JsonResult<PayDO> searchPay(PayQueryParam param);

    JsonResult<PayDetailDO> searchPayDetail(int payId);

    PayDetailDO getDetailById(int id);

    ResultCodeEnum insertDetail(PayDetailDO item);

    ResultCodeEnum updateDetail(PayDetailDO item);
}

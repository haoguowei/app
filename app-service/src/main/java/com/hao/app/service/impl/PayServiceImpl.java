package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.PayQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.PayStatusEnum;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.PayDetailMapper;
import com.hao.app.dao.PayMapper;
import com.hao.app.pojo.PayDO;
import com.hao.app.pojo.PayDetailDO;
import com.hao.app.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Resource
    private PayDetailMapper payDetailMapper;

    @Override
    public ResultCodeEnum insert(PayDO item) {
        int res = payMapper.insert(item);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(PayDO item) {
        PayDO old = payMapper.selectByPrimaryKey(item.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = payMapper.update(item);
        if (res > 0) {
            updateTotals(old.getId());
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    //根据payid 重新获取总金额，总人数
    private int updateTotals(int payId) {
        List<PayDetailDO> detailList = payDetailMapper.search(payId);
        if (detailList == null) {
            detailList = new ArrayList<>();
        }

        int totalMan = detailList.size();
        PayStatusEnum payStatusEnum = PayStatusEnum.YES;
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        for (PayDetailDO item : detailList) {
            if (item.getPayStatus() == null || item.getPayStatus().equals(PayStatusEnum.NO.getCode())) {
                payStatusEnum = PayStatusEnum.NO;
            }

            if (item.getPayAmount() != null) {
                totalAmount = totalAmount.add(item.getPayAmount());
            }
        }

        if (totalMan == 0) {
            payStatusEnum = PayStatusEnum.NO;
        }

        return payMapper.updateTotals(payId, totalMan, payStatusEnum.getCode(), totalAmount);

    }

    @Override
    public PayDO getById(Integer id) {
        if (id != null && id > 0) {
            return payMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public JsonResult<PayDO> searchPay(PayQueryParam param) {
        int count = payMapper.count(param);
        List<PayDO> list = payMapper.search(param);
        return new JsonResult<>(count, list);
    }
}

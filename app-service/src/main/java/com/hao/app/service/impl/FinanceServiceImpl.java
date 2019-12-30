package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.FinanceQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.FinanceMapper;
import com.hao.app.pojo.FinanceDO;
import com.hao.app.service.FinanceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private FinanceMapper financeMapper;


    @Override
    public JsonResult<FinanceDO> searchFinance(FinanceQueryParam param) {
        if (StringUtils.isNotBlank(param.getFromDay())) {
            param.setFromDay(param.getFromDay() + "-01");
        }
        if (StringUtils.isNotBlank(param.getEndDay())) {
            param.setEndDay(param.getEndDay() + "-01");
        }

        int count = financeMapper.count(param);
        List<FinanceDO> list = financeMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public FinanceDO getById(int id) {
        return financeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(FinanceDO finance) {
        int res = financeMapper.insert(finance);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(FinanceDO finance) {
        FinanceDO old = financeMapper.selectByPrimaryKey(finance.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = financeMapper.update(finance);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public String searchFinance4HJ(FinanceQueryParam param) {
        Map<String, Object> map = financeMapper.searchHJ(param);
        BigDecimal a = BigDecimal.valueOf(0);
        BigDecimal b = BigDecimal.valueOf(0);
        if (map != null) {
            a = (BigDecimal) map.get("a");
            b = (BigDecimal) map.get("b");
        }

        StringBuffer sbr = new StringBuffer();
        sbr.append("收入金额合计:").append(a).append("元，")
                .append("支出金额合计:").append(b.doubleValue()).append("元，")
                .append("净流量金额合计:").append(a.subtract(b).doubleValue()).append("元")
        ;
        return sbr.toString();
    }
}

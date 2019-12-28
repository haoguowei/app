package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.FinanceMapper;
import com.hao.app.pojo.FinanceDO;
import com.hao.app.service.FinanceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private FinanceMapper financeMapper;


    @Override
    public JsonResult<FinanceDO> searchFinance(Integer projectsId, String fromDay, String endDay) {
        if (StringUtils.isNotBlank(fromDay)) {
            fromDay = fromDay + "-01";
        }
        if (StringUtils.isNotBlank(endDay)) {
            endDay = endDay + "-01";
        }
        List<FinanceDO> list = financeMapper.search(projectsId, fromDay, endDay);
        return new JsonResult<>(list == null ? 0 : list.size(), list);
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
}

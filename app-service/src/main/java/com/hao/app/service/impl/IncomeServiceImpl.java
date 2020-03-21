package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.IncomeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.IncomeMapper;
import com.hao.app.pojo.IncomeDO;
import com.hao.app.service.IncomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Resource
    private IncomeMapper incomeMapper;


    @Override
    public JsonResult<IncomeDO> search(IncomeQueryParam param) {
        int count = incomeMapper.count(param);
        List<IncomeDO> list = incomeMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public IncomeDO getById(int id) {
        return incomeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(IncomeDO cost) {
        int res = incomeMapper.insert(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(IncomeDO cost) {
        IncomeDO old = incomeMapper.selectByPrimaryKey(cost.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = incomeMapper.update(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}

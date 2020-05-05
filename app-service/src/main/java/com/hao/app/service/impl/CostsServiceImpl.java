package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.CostsMapper;
import com.hao.app.pojo.CostsDO;
import com.hao.app.service.CostsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CostsServiceImpl implements CostsService {

    @Resource
    private CostsMapper costsMapper;

    @Override
    public JsonResult<CostsDO> searchYYCost(CostQueryParam param) {
        int count = costsMapper.count(param);
        List<CostsDO> list = costsMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public CostsDO getById(int id) {
        return costsMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(CostsDO cost) {
        int res = costsMapper.insert(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(CostsDO cost) {
        CostsDO old = costsMapper.selectByPrimaryKey(cost.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = costsMapper.update(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}

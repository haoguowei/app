package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.YYCostMapper;
import com.hao.app.pojo.YYCostDO;
import com.hao.app.service.YYCostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class YYCostServiceImpl implements YYCostService {

    @Resource
    private YYCostMapper yYCostMapper;


    @Override
    public JsonResult<YYCostDO> searchYYCost() {
        return null;
    }

    @Override
    public YYCostDO getById(int id) {
        return yYCostMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(YYCostDO cost) {
        int res = yYCostMapper.insert(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(YYCostDO cost) {
        YYCostDO old = yYCostMapper.selectByPrimaryKey(cost.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = yYCostMapper.update(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}

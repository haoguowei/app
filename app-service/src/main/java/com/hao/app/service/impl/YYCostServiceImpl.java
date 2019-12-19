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
        return null;
    }

    @Override
    public ResultCodeEnum insert(YYCostDO cost) {
        return null;
    }

    @Override
    public ResultCodeEnum update(YYCostDO cost) {
        return null;
    }
}

package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.YunyingCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.YunyingCostMapper;
import com.hao.app.pojo.YunyingCostDO;
import com.hao.app.service.YunyingCostService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class YunyingCostServiceImpl implements YunyingCostService {

    @Resource
    private YunyingCostMapper yunyingCostMapper;

    @Override
    public JsonResult<YunyingCostDO> searchCost(YunyingCostQueryParam param) {
        int count = yunyingCostMapper.count(param);
        List<YunyingCostDO> list = yunyingCostMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public YunyingCostDO getById(int id) {
        return yunyingCostMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(YunyingCostDO cost) {
        int res = yunyingCostMapper.insert(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(YunyingCostDO cost) {
        YunyingCostDO old = yunyingCostMapper.selectByPrimaryKey(cost.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = yunyingCostMapper.update(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public String searchCost4HJ(YunyingCostQueryParam param) {
        Map<String, Object> map = yunyingCostMapper.searchHJ(param);
        BigDecimal a = BigDecimal.valueOf(0);
        if (map != null) {
            a = (BigDecimal) map.get("a");
        }

        StringBuffer sbr = new StringBuffer();
        sbr.append("合计:").append(a).append("元")
        ;
        return sbr.toString();
    }

    @Override
    public List<ResultStatistics> searchTotalPay(YunyingCostQueryParam param) {
        return yunyingCostMapper.searchTotalPay(param);
    }

    @Override
    public List<ResultStatistics> searchTotalPayForProjects(YunyingCostQueryParam param) {
        return yunyingCostMapper.searchTotalPayForProjects(param);
    }
}

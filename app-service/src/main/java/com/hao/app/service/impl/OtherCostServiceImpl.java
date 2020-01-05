package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.OtherCostMapper;
import com.hao.app.pojo.OtherCostDO;
import com.hao.app.service.OtherCostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OtherCostServiceImpl implements OtherCostService {

    @Resource
    private OtherCostMapper otherCostMapper;

    @Override
    public JsonResult<OtherCostDO> searchCost(OtherCostQueryParam param) {
        int count = otherCostMapper.count(param);
        List<OtherCostDO> list = otherCostMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public OtherCostDO getById(int id) {
        return otherCostMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(OtherCostDO cost) {
        int res = otherCostMapper.insert(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(OtherCostDO cost) {
        OtherCostDO old = otherCostMapper.selectByPrimaryKey(cost.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = otherCostMapper.update(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public String searchCost4HJ(OtherCostQueryParam param) {
        Map<String, Object> map = otherCostMapper.searchHJ(param);
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
    public List<ResultStatistics> searchTotalPay(OtherCostQueryParam param) {
        return otherCostMapper.searchTotalPay(param);
    }
}

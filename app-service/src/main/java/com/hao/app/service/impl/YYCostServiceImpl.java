package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AssetsMapper;
import com.hao.app.dao.YYCostMapper;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.pojo.YYCostDO;
import com.hao.app.service.YYCostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class YYCostServiceImpl implements YYCostService {

    @Resource
    private YYCostMapper yYCostMapper;

    @Resource
    private AssetsMapper assetsMapper;


    @Override
    public JsonResult<YYCostDO> searchYYCost(CostQueryParam param) {
        int count = yYCostMapper.count(param);
        List<YYCostDO> list = yYCostMapper.search(param);

        if (list != null) {
            for (YYCostDO cost : list) {
                if (cost.getAssetId() > 0) {
                    AssetsDO assetsDO = assetsMapper.selectByPrimaryKey(cost.getAssetId());
                    cost.setAssetsInfo(assetsDO.getName() + "(资产编号:" + assetsDO.getNumber() + "，牌照号:" + assetsDO.getLicense() + ")");
                }

                //日行驶里程，日末-日初
                int x = cost.getEndMileage() - cost.getStartMileage();
                x = x < 0 ? 0 : x;
                cost.setDayMileage(x);


                //平均油耗：（加油量/日行驶里程）* 100
                if (x == 0) {
                    cost.setAvgFuel(BigDecimal.valueOf(0));
                } else {
                    BigDecimal y = BigDecimal.valueOf(cost.getFuel()).multiply(BigDecimal.valueOf(100.00)).divide(BigDecimal.valueOf(x), 2, BigDecimal.ROUND_HALF_UP);
                    cost.setAvgFuel(y);
                }
            }
        }

        return new JsonResult<>(count, list);
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

    @Override
    public String searchYYCost4HJ(CostQueryParam param) {
        Map<String, Object> map = yYCostMapper.searchHJ(param);
        BigDecimal a = BigDecimal.valueOf(0);
        BigDecimal b = BigDecimal.valueOf(0);
        if (map != null) {
            a = (BigDecimal) map.get("a");
            b = (BigDecimal) map.get("b");
        }

        StringBuffer sbr = new StringBuffer();
        sbr.append("日行驶里程合计:").append(a).append("");
        sbr.append("消费合计:").append(b).append("元")
        ;
        return sbr.toString();
    }

    @Override
    public BigDecimal searchTotalPay(CostQueryParam param) {
        Map<String, Object> map = yYCostMapper.searchHJ(param);
        BigDecimal b = BigDecimal.valueOf(0);
        if (map != null) {
            b = (BigDecimal) map.get("b");
        }
        return b;
    }

    @Override
    public List<ResultStatistics> searchTotalPayForProjects(OtherCostQueryParam param) {
        return yYCostMapper.searchTotalPayForProjects(param);
    }
}

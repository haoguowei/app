package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AssetsMapper;
import com.hao.app.dao.YYCostMapper;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.pojo.YYCostDO;
import com.hao.app.service.YYCostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}

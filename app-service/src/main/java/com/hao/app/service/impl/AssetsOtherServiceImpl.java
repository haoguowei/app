package com.hao.app.service.impl;


import com.hao.app.commons.entity.param.AssetsOtherQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AssetsOtherMapper;
import com.hao.app.pojo.AssetsOtherDO;
import com.hao.app.service.AssetsOtherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public class AssetsOtherServiceImpl implements AssetsOtherService {
    @Resource
    private AssetsOtherMapper assetsOtherMapper;


    @Override
    public JsonResult<AssetsOtherDO> searchAssets(AssetsOtherQueryParam param) {
        int count = assetsOtherMapper.count(param);
        List<AssetsOtherDO> list = assetsOtherMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public AssetsOtherDO getById(int id) {
        return assetsOtherMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(AssetsOtherDO assets) {
        int res = assetsOtherMapper.insert(assets);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(AssetsOtherDO assets) {
        AssetsOtherDO old = assetsOtherMapper.selectByPrimaryKey(assets.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = assetsOtherMapper.update(assets);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public String searchAssets4HJ(AssetsOtherQueryParam param) {
        Map<String, Object> map = assetsOtherMapper.searchHJ(param);
        BigDecimal a = BigDecimal.valueOf(0);
        BigDecimal b = BigDecimal.valueOf(0);
        if (map != null) {
            a = (BigDecimal) map.get("a");
            b = (BigDecimal) map.get("b");
        }

        StringBuffer sbr = new StringBuffer();
        sbr.append("数量合计:")
                .append(a.doubleValue()).append("，")
                .append("现况数量合计:").append(b).append("");
        return sbr.toString();
    }
}

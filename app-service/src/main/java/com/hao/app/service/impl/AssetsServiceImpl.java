package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AssetsMapper;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.service.AssetsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Resource
    private AssetsMapper assetsMapper;


    @Override
    public JsonResult<AssetsDO> searchAssets(AssetsQueryParam param) {
        int count = assetsMapper.count(param);
        List<AssetsDO> list = assetsMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public AssetsDO getById(int id) {
        return assetsMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(AssetsDO assets) {
        int res = assetsMapper.insert(assets);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(AssetsDO assets) {
        AssetsDO old = assetsMapper.selectByPrimaryKey(assets.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = assetsMapper.update(assets);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}

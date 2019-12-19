package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AssetsMapper;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.service.AssetsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Resource
    private AssetsMapper assetsMapper;


    @Override
    public JsonResult<AssetsDO> searchAssets() {
        return null;
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
        int res = assetsMapper.update(assets);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}

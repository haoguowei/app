package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AssetsDO;

public interface AssetsService {

    JsonResult<AssetsDO> searchAssets();

    AssetsDO getById(int id);

    ResultCodeEnum insert(AssetsDO assets);

    ResultCodeEnum update(AssetsDO assets);
}

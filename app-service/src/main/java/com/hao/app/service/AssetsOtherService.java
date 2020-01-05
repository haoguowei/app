package com.hao.app.service;

import com.hao.app.commons.entity.param.AssetsOtherQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AssetsOtherDO;

public interface AssetsOtherService {

    JsonResult<AssetsOtherDO> searchAssets(AssetsOtherQueryParam param);

    AssetsOtherDO getById(int id);

    ResultCodeEnum insert(AssetsOtherDO assets);

    ResultCodeEnum update(AssetsOtherDO assets);

    String searchAssets4HJ(AssetsOtherQueryParam param);
}

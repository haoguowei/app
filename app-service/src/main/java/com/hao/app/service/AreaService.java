package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AreaDO;

public interface AreaService {

    JsonResult<AreaDO> searchAreas();

    AreaDO getById(int id);

    ResultCodeEnum insert(AreaDO area);

    ResultCodeEnum update(AreaDO area);
}

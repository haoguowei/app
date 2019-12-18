package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AreaMapper;
import com.hao.app.pojo.AreaDO;
import com.hao.app.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public JsonResult<AreaDO> searchAreas() {
        List<AreaDO> list = areaMapper.searchAreas();
        return new JsonResult<>(list == null ? 0 : list.size(), list);
    }

    @Override
    public AreaDO getById(int id) {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(AreaDO area) {
        int res = areaMapper.insert(area);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(AreaDO area) {
        int res = areaMapper.update(area);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}

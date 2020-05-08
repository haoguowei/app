package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.CostsMapper;
import com.hao.app.dao.CostsTypeMapper;
import com.hao.app.pojo.CostsDO;
import com.hao.app.pojo.CostsTypeDO;
import com.hao.app.service.CostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CostsServiceImpl implements CostsService {

    @Resource
    private CostsMapper costsMapper;

    @Resource
    private CostsTypeMapper costsTypeMapper;

    @Override
    public Map<Integer, String> mapCostsType() {
        List<CostsTypeDO> list = costsTypeMapper.search();
        Map<Integer, String> map = new HashMap<>(list.size());
        for (CostsTypeDO ct : list) {
            map.put(ct.getId(), ct.getName());
        }
        return map;
    }

    @Override
    public List<CostsTypeDO> listCostsTypeByParentId(Integer parentId) {
        return costsTypeMapper.searchByParentId(parentId == null ? 0 : parentId);
    }


    @Override
    public JsonResult<CostsDO> searchCosts(CostQueryParam param) {
        int count = costsMapper.count(param);
        List<CostsDO> list = costsMapper.search(param);
        if (list != null) {
            Map<Integer, String> map = mapCostsType();
            for (CostsDO costs : list) {
                String str1 = map.get(costs.getType1());
                String str2 = map.get(costs.getType2());
                String str3 = map.get(costs.getType3());
                costs.setType1Str(StringUtils.isBlank(str1) ? "" : str1);
                costs.setType2Str(StringUtils.isBlank(str2) ? "" : str2);
                costs.setType3Str(StringUtils.isBlank(str3) ? "" : str3);
            }
        }
        return new JsonResult<>(count, list);
    }

    @Override
    public CostsDO getById(int id) {
        return costsMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(CostsDO cost) {
        int res = costsMapper.insert(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(CostsDO cost) {
        CostsDO old = costsMapper.selectByPrimaryKey(cost.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = costsMapper.update(cost);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}

package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.AmountTable;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.CostsMapper;
import com.hao.app.dao.CostsTypeMapper;
import com.hao.app.pojo.CostsDO;
import com.hao.app.pojo.CostsTypeDO;
import com.hao.app.service.CostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CostsServiceImpl implements CostsService {

    @Resource
    private CostsMapper costsMapper;

    @Resource
    private CostsTypeMapper costsTypeMapper;

    @Override
    public Map<TableKey, BigDecimal> getCostTable(TableQueryParam param) {
        List<AmountTable> list = costsMapper.searchCostTable(param);
        Map<TableKey, BigDecimal> map = new HashMap<>();
        if (list != null) {
            for (AmountTable table : list) {
                map.put(new TableKey(table.getProjects(), table.getType3()), table.getAmount());
            }
        }
        return map;
    }

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
    public List<CostsTypeDO> getTableTypes() {
        List<CostsTypeDO> result = new ArrayList<>();
        Set<Integer> allLeafIds = new HashSet<>();

        List<CostsTypeDO> type1s = costsTypeMapper.searchByParentId(0);
        for (int i = 0; i < type1s.size(); i++) {
            CostsTypeDO type1 = type1s.get(i);
            Set<Integer> leafIds = new HashSet<>();

            List<CostsTypeDO> type2s = costsTypeMapper.searchByParentId(type1.getId());
            for (int j = 0; j < type2s.size(); j++) {
                CostsTypeDO type2 = type2s.get(j);

                List<CostsTypeDO> type3s = costsTypeMapper.searchByParentId(type2.getId());
                for (int z = 0; z < type3s.size(); z++) {
                    CostsTypeDO type3 = type3s.get(z);
                    leafIds.add(type3.getId());
                    allLeafIds.add(type3.getId());

                    if (z == 0) {
                        type3.setName2(type2.getName());
                    }

                    if (j == 0 && z == 0) {
                        type3.setName1(type1.getName());

                    }

                    type3.setName3(type3.getName());
                    result.add(type3);
                }
            }

            CostsTypeDO heji = new CostsTypeDO();
            heji.setId(-1);
            heji.setName2(type1.getName() + "合计");
            heji.setLeafIds(leafIds);
            heji.setBackground("background-color: #F8F8FF;");
            result.add(heji);

            CostsTypeDO zhanbi = new CostsTypeDO();
            zhanbi.setId(-2);
            zhanbi.setName2(type1.getName() + "占比");
            zhanbi.setLeafIds(leafIds);
            zhanbi.setBackground("background-color: #F8F8FF;");
            result.add(zhanbi);
        }


        CostsTypeDO chengguo1 = new CostsTypeDO();
        chengguo1.setId(-101);
        chengguo1.setName1("经营成果");
        chengguo1.setName2("费用合计");
        chengguo1.setLeafIds(allLeafIds);
        chengguo1.setBackground("background-color: #D8BFD8;");
        result.add(chengguo1);

        CostsTypeDO chengguo2 = new CostsTypeDO();
        chengguo2.setId(-102);
        chengguo2.setName2("费用合计占比");
        chengguo2.setLeafIds(allLeafIds);
        chengguo2.setBackground("background-color: #D8BFD8;");
        result.add(chengguo2);

        CostsTypeDO chengguo3 = new CostsTypeDO();
        chengguo3.setId(-103);
        chengguo3.setName2("利润率");
        chengguo3.setLeafIds(allLeafIds);
        chengguo3.setBackground("background-color: #D8BFD8;");
        result.add(chengguo3);

        return result;
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

    @Override
    public boolean updateStatus(int id) {
        int v = costsMapper.updateStatus(id);
        return v > 0;
    }
}
